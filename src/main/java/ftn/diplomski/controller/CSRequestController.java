/**
 * 
 */
package ftn.diplomski.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ftn.diplomski.entity.ChangeSettings;
import ftn.diplomski.entity.User;
import ftn.diplomski.service.ChangeSettingsService;
import ftn.diplomski.service.UserService;

/**
 * @author Boki on Jan 24, 2018
 *
 */
@Controller
@RequestMapping("/requests")
@PreAuthorize("hasRole('ADMIN')")
public class CSRequestController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private ChangeSettingsService csrService;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String requests(Model model) {
		List<ChangeSettings> csRequests = csrService.findAll();
		List<ChangeSettings> archivedRequests = csrService.findArhived();
		List<ChangeSettings> activeRequests = csrService.findActive();
		model.addAttribute("requests", csRequests);
		model.addAttribute("archivedRequests", archivedRequests);
		model.addAttribute("activeRequests", activeRequests);
		
		return "requests";
	}
	
	@RequestMapping(value = "/accept", method = RequestMethod.GET)
	public String acceptRequest(@RequestParam("id")Long id) {
		ChangeSettings csRequest = csrService.findOne(id);
		User user = userService.findByUsername(csRequest.getUsername());
		user.setFirstName(csRequest.getFirstName());
		user.setLastName(csRequest.getLastName());
		user.setEmail(csRequest.getEmail());
		user.setDoctorUsername(csRequest.getDoctor());
		userService.saveUser(user);
		csRequest.setConfirmed(true);
		csRequest.setArchived(true);
		csrService.updateCSR(csRequest);
		return "redirect:/requests";
	}
	
	@RequestMapping(value = "/discard", method = RequestMethod.GET)
	public String discardRequest(@RequestParam("id")Long id) {
		ChangeSettings csRequest = csrService.findOne(id);
		csRequest.setConfirmed(false);
		csRequest.setArchived(true);
		csrService.updateCSR(csRequest);
		return "redirect:/requests";
	}
	
}
