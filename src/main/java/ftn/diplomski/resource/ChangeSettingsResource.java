/**
 * 
 */
package ftn.diplomski.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSet;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ftn.diplomski.entity.ChangeSettings;
import ftn.diplomski.service.ChangeSettingsService;

/**
 * @author Boki on Jan 23, 2018
 *
 */

@RestController
@RequestMapping("api/settings")
public class ChangeSettingsResource {

	@Autowired
	private ChangeSettingsService changeSettingsService;
	
	@RequestMapping(value = "/newRequest", method = RequestMethod.POST)
	public ChangeSettings createCSR(@RequestBody ChangeSettings csr) {	//CSR - ChangeServiceRequest
		ChangeSettings newCSR = new ChangeSettings();
		newCSR.setFirstName(csr.getFirstName());
		newCSR.setLastName(csr.getLastName());
		newCSR.setEmail(csr.getEmail());
		newCSR.setDoctor(csr.getDoctor());
		newCSR.setUsername(csr.getUsername());
		return changeSettingsService.createCSR(newCSR);
	}
}
