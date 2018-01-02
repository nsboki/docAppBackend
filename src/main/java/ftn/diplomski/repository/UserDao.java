/**
 * 
 */
package ftn.diplomski.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ftn.diplomski.entity.User;

/**
 * @author Boki on Dec 15, 2017
 *
 */
public interface UserDao extends CrudRepository<User, Long>{
	User findByUsername(String username);
	User findByEmail(String email);
	List<User> findAll();
	
}
