/**
 * 
 */
package ftn.diplomski.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.diplomski.entity.ChangeSettings;
import ftn.diplomski.repository.ChangeSettingsRequestDao;
import ftn.diplomski.repository.UserDao;

/**
 * @author Boki on Jan 23, 2018
 *
 */
@Service
public class ChangeSettingsServiceImpl implements ChangeSettingsService {

	@Autowired
	private ChangeSettingsRequestDao csrDao;
	
	@Autowired
	private UserDao userDao;

	@Override
	public ChangeSettings createCSR(ChangeSettings newCSR) {
		return csrDao.save(newCSR);
	}

	@Override
	public List<ChangeSettings> findAll() {
		return csrDao.findAll();
	}

	@Override
	public ChangeSettings findOne(Long id) {
		return csrDao.findOne(id);
	}

	@Override
	public void updateCSR(ChangeSettings csRequest) {
		csrDao.save(csRequest);
		
	}

	@Override
	public List<ChangeSettings> findArhived() {
		return csrDao.findByArchived(true);
	}

	@Override
	public List<ChangeSettings> findActive() {
		return csrDao.findByArchived(false);
	}
	

}
