/**
 * 
 */
package ftn.diplomski.service;

import ftn.diplomski.entity.User;
import ftn.diplomski.entity.security.UserRole;

/**
 * @author Boki on Jan 24, 2018
 *
 */
public interface UserRoleService {

	/**
	 * @param userId
	 * @return
	 */
	UserRole findByUser(User user);

	/**
	 * @param userRole
	 */
	void update(UserRole userRole);


}
