/**
 * 
 */
package ftn.diplomski.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.diplomski.entity.User;
import ftn.diplomski.entity.security.UserRole;
import ftn.diplomski.repository.UserRoleDao;

/**
 * @author Boki on Jan 24, 2018
 *
 */
@Service
public class UserRoleServiceImpl implements UserRoleService {

	@Autowired
	private UserRoleDao urDao;
	
	@Override
	public UserRole findByUser(User user) {
		return urDao.findByUser(user);
	}

	/* (non-Javadoc)
	 * @see ftn.diplomski.service.UserRoleService#update(ftn.diplomski.entity.security.UserRole)
	 */
	@Override
	public void update(UserRole userRole) {
		urDao.save(userRole);
	}


}
