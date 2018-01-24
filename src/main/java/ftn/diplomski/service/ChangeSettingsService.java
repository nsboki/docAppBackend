/**
 * 
 */
package ftn.diplomski.service;

import java.util.List;

import ftn.diplomski.entity.ChangeSettings;

/**
 * @author Boki on Jan 23, 2018
 *
 */
public interface ChangeSettingsService {

	/**
	 * @param newCSR
	 * @return
	 */
	ChangeSettings createCSR(ChangeSettings newCSR);

	/**
	 * @return
	 */
	List<ChangeSettings> findAll();

	/**
	 * @param id
	 * @return
	 */
	ChangeSettings findOne(Long id);

	/**
	 * @param csRequest
	 */
	void updateCSR(ChangeSettings csRequest);

	/**
	 * @return
	 */
	List<ChangeSettings> findArhived();

	/**
	 * @return
	 */
	List<ChangeSettings> findActive();

}
