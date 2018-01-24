/**
 * 
 */
package ftn.diplomski.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ftn.diplomski.entity.User;
import ftn.diplomski.entity.security.Role;
import ftn.diplomski.entity.security.UserRole;
import ftn.diplomski.service.RoleService;
import ftn.diplomski.service.UserRoleService;
import ftn.diplomski.service.UserService;

/**
 * @author Boki on Dec 26, 2017
 *
 */
@Controller
@RequestMapping("/user")
@PreAuthorize("hasRole('ADMIN')")
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private UserRoleService urService;
	
	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public String profile(Principal principal, Model model) {
		User user = userService.findByUsername(principal.getName());
		
		model.addAttribute("user", user);
		
		return "profile";
	}
	
	@RequestMapping(value = "/profile", method = RequestMethod.POST)
	public String profilePost(@ModelAttribute("user") User newUser, Model model) {
		User user = userService.findByUsername(newUser.getUsername());
		user.setUsername(newUser.getUsername());
		user.setFirstName(newUser.getFirstName());
		user.setLastName(newUser.getLastName());
		user.setEmail(newUser.getEmail());
		
		model.addAttribute("user", user);
		
		userService.saveUser(user);
		
		return "profile";
	}
	
	@RequestMapping(value = "/accounts", method = RequestMethod.GET)
	public String allAccounts(Model model) {
		List<User> users = userService.findUserList();
		model.addAttribute("users", users);
		
		return "users";
	}
	
	@RequestMapping(value = "/accounts/user", method = RequestMethod.GET)
	public String user(@RequestParam("username") String username, Model model) {
		User user = userService.findByUsername(username);
		String role = userService.getUserRole(username);
		List<Role> roles = roleService.findAll();
		model.addAttribute("user", user);
		model.addAttribute("role", role);
		model.addAttribute("roles", roles);
		model.addAttribute("newRole", "");
		model.addAttribute("username", username);
		return "user";
	}
	
	@RequestMapping(value = "/accounts/user", method = RequestMethod.POST)
	public String changeRole(@ModelAttribute("username")String username, @ModelAttribute("newRole") String roleName, Model model) {
		User tempUser = userService.findByUsername(username);
		UserRole userRole = urService.findByUser(tempUser);
		Role role = roleService.findByName(roleName);
		userRole.setRole(role);
		urService.update(userRole);
		return "redirect:/user/accounts";
	}

	
}
