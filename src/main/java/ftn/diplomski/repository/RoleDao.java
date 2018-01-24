/**
 * 
 */
package ftn.diplomski.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ftn.diplomski.entity.security.Role;

/**
 * @author Boki on Dec 20, 2017
 *
 */
public interface RoleDao extends CrudRepository<Role, Integer> {
	Role findByName(String name);
	List<Role> findAll();
	
}
