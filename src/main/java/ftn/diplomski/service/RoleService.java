/**
 * 
 */
package ftn.diplomski.service;

import java.util.List;

import ftn.diplomski.entity.security.Role;

/**
 * @author Boki on Jan 12, 2018
 *
 */
public interface RoleService {

	/**
	 * @param roleId
	 */
	String getRole(int roleId);

	/**
	 * @return
	 */
	List<Role> findAll();

	/**
	 * @param rolena
	 * @return
	 */
	Role findByName(String rolena);

}
