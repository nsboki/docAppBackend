/**
 * 
 */
package ftn.diplomski.service;

import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ftn.diplomski.entity.User;
import ftn.diplomski.entity.security.UserRole;
import ftn.diplomski.repository.RoleDao;
import ftn.diplomski.repository.UserDao;

/**
 * @author Boki on Dec 15, 2017
 *
 */

@Service
@Transactional
public class UserServiceImpl implements UserService {
	
	private static final Logger LOG = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	public void save(User user) {
		userDao.save(user);
	}
	
	public User findByUsername(String username) {
		return userDao.findByUsername(username);
	}
	
	public User findByEmail(String email) {
		return userDao.findByEmail(email);
	}
	
	public boolean checkUserExists(String username, String email) {
		if (checkUsernameExists(username) || checkEmailExists(email)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @param username
	 * @return
	 */
	public boolean checkUsernameExists(String username) {
		if(findByUsername(username) != null) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @param email
	 * @return
	 */
	public boolean checkEmailExists(String email) {
		if(findByEmail(email) != null) {
			return true;
		} else {
			return false;
		}
	}

	/* (non-Javadoc)
	 * @see ftn.diplomski.service.UserService#createUser(ftn.diplomski.entity.User)
	 */
	@Override
	
	public User createUser(User user, Set<UserRole> userRoles) {
		User localUser = userDao.findByUsername(user.getUsername());
		
		if(localUser != null) {
			LOG.info("User with username {} already exist. Nothing will be done. ", user.getUsername());
		} else {
			String encriptedPassword = passwordEncoder.encode(user.getPassword());
			user.setPassword(encriptedPassword);
			
			for(UserRole ur: userRoles) {
				roleDao.save(ur.getRole());
			}
			
			user.getUserRoles().addAll(userRoles);
			
			localUser = userDao.save(user);
		}
		
		return localUser;
		
	}

	/* (non-Javadoc)
	 * @see ftn.diplomski.service.UserService#saveUser(ftn.diplomski.entity.User)
	 */
	@Override
	public User saveUser(User user) {
		return userDao.save(user);		
	}

	/* (non-Javadoc)
	 * @see ftn.diplomski.service.UserService#findUserList()
	 */
	@Override
	public List<User> findUserList() {
		return userDao.findAll();
	}

	/* (non-Javadoc)
	 * @see ftn.diplomski.service.UserService#enableUser(java.lang.String)
	 */
	@Override
	public void enableUser(String username) {
		User user = findByUsername(username);
        user.setEnabled(true);
        userDao.save(user);		
	}

	/* (non-Javadoc)
	 * @see ftn.diplomski.service.UserService#disableUser(java.lang.String)
	 */
	@Override
	public void disableUser(String username) {
		User user = findByUsername(username);
        user.setEnabled(false);
        System.out.println(user.isEnabled());
        userDao.save(user);
        System.out.println(username + " is disabled.");		
	}

}
