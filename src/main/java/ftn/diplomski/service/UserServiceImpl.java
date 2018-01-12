/**
 * 
 */
package ftn.diplomski.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ftn.diplomski.entity.User;
import ftn.diplomski.entity.security.Role;
import ftn.diplomski.entity.security.UserRole;
import ftn.diplomski.repository.RoleDao;
import ftn.diplomski.repository.UserDao;
import ftn.diplomski.repository.UserRoleDao;

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
	private UserRoleDao userRoleDao;
	
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
	
	public User updateUser(User user, List<UserRole> userRoles) {
		for(UserRole ur: userRoles) {
			roleDao.save(ur.getRole());
		}
		user.getUserRoles().addAll(userRoles);
		return userDao.save(user);
	}

	@Override
	public User saveUser(User user) {
		return userDao.save(user);		
	}

	@Override
	public List<User> findUserList() {
		return userDao.findAll();
	}

	@Override
	public void enableUser(String username) {
		User user = findByUsername(username);
        user.setEnabled(true);
        userDao.save(user);		
	}

	@Override
	public void disableUser(String username) {
		User user = findByUsername(username);
        user.setEnabled(false);
        System.out.println(user.isEnabled());
        userDao.save(user);
        System.out.println(username + " is disabled.");		
	}

	@Override
	public User findById(Long doctorId) {
		return userDao.findOne(doctorId);
	}

	@Override
	public List<User> findDoctorList() {
		List<UserRole> userRoleList = userRoleDao.findByRole(roleDao.findByName("ROLE_DOCTOR"));
		List<User> doctorList = new ArrayList();
		for (UserRole userRole : userRoleList) {
			User doctor = userRole.getUser();
			doctorList.add(doctor);
		}
		return doctorList;
	}

	@Override
	public List<User> findDoctorPatients(String username) {
		return userDao.findByDoctorUsername(username);
	}

	/* (non-Javadoc)
	 * @see ftn.diplomski.service.UserService#getUserRole(java.lang.String)
	 */
	@Override
	public String getUserRole(String username) {
		User user= userDao.findByUsername(username);
		UserRole userRole = userRoleDao.findByUser(user);
		Role role = roleDao.findOne(userRole.getRole().getRoleId());
		System.out.println(role.getName());
		return role.getName();
	}

}
