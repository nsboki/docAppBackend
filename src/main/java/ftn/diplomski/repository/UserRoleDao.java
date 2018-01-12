/**
 * 
 */
package ftn.diplomski.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ftn.diplomski.entity.User;
import ftn.diplomski.entity.security.Role;
import ftn.diplomski.entity.security.UserRole;

/**
 * @author Boki on Jan 9, 2018
 *
 */
public interface UserRoleDao extends CrudRepository<UserRole, Long>{
	UserRole findByUser(User user);
	List<UserRole> findByRole(Role role);
}
