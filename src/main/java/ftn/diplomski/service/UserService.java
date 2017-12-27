/**
 * 
 */
package ftn.diplomski.service;

import java.util.Set;

import ftn.diplomski.entity.User;
import ftn.diplomski.entity.security.UserRole;

/**
 * @author Boki on Dec 15, 2017
 *
 */
public interface UserService {

	User findByUsername(String username);
	
	User findByEmail(String email);
	
	boolean checkUserExists(String username, String email);
	
	boolean checkUsernameExists(String username);
	
	boolean checkEmailExists(String email);
	
	void save(User user);

	/**
	 * @param user
	 */
	User createUser(User user, Set<UserRole> userRoles);

	/**
	 * @param user
	 * @return 
	 */
	User saveUser(User user);
	
}
