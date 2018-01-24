/**
 * 
 */
package ftn.diplomski.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ftn.diplomski.entity.ChangeSettings;

/**
 * @author Boki on Jan 23, 2018
 *
 */
public interface ChangeSettingsRequestDao extends CrudRepository<ChangeSettings, Long>{

	List<ChangeSettings> findAll();

	/**
	 * @param b
	 * @return
	 */
	List<ChangeSettings> findByArchived(boolean b);
}
