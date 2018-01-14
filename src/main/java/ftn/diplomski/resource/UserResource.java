/**
 * 
 */
package ftn.diplomski.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ftn.diplomski.entity.User;
import ftn.diplomski.entity.security.UserRole;
import ftn.diplomski.repository.UserRoleDao;
import ftn.diplomski.service.RoleService;
import ftn.diplomski.service.UserService;

/**
 * @author Boki on Dec 28, 2017
 *
 */
@RestController
@RequestMapping("/api")
//@PreAuthorize("hasRole('ADMIN')")
public class UserResource {

    @Autowired
    private UserService userService;
    
    @Autowired
    private UserRoleDao userRoleDao;
    
    @Autowired
    private RoleService roleService;

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/user/all", method = RequestMethod.GET)
    public List<User> userList() {
        return userService.findUserList();
    }
    
    
    @RequestMapping(value = "/user/doctor/all", method = RequestMethod.GET)
    public List<User> doctorList() {
        return userService.findDoctorList();
    }
    
    @PreAuthorize("hasRole('DOCTOR')")    
    @RequestMapping(value = "/user/patient/{username}")
    public List<User> patientList(@PathVariable("username") String username) {
    	return userService.findDoctorPatients(username);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping("/user/{username}/enable")
    public void enableUser(@PathVariable("username") String username) {
        userService.enableUser(username);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping("/user/{username}/disable")
    public void diableUser(@PathVariable("username") String username) {
        userService.disableUser(username);
    }
    
    
    @PreAuthorize("hasAnyRole('ADMIN','DOCTOR','USER')")
    @RequestMapping("/user/{username}")
    public User getUser(@PathVariable("username") String username) {
    	return userService.findByUsername(username);
    }
    
    
//    @RequestMapping(value = "/user/{username}", method = RequestMethod.POST)
//    public void updateUser(@PathVariable("username")String username, @RequestBody User user) {
//    	User newUser = userService.findByUsername(username);
//    	System.out.println(user);
//    	newUser.setFirstName(user.getFirstName());
//    	userService.saveUser(newUser);
//    }
    
//    @PreAuthorize("hasAnyRole('ADMIN','DOCTOR','USER')")
    @RequestMapping("/user/role/{username}")
    public String getUserRole(@PathVariable("username")String  username) {
//    	UserRole userRole = userRoleDao.findByUser(username);
    	return userService.getUserRole(username);
    }
    
    //    let url = ServerUrl + "/api/user/credentials/"+username;

    @RequestMapping("/user/credentials/{username}")
    public boolean checkCredentials(@PathVariable("usernaem")String username, @RequestBody String password) {
    	return userService.checkCredentials(username, password);
    }
    
//    @RequestMapping(value = "/user/{username}", method = RequestMethod.POST)
//    public void saveUser(@PathVariable("username") String username, @RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {
//    	User tempUser = userService.findByUsername(username);
//    	tempUser.setFirstName(firstName);
//    	tempUser.setLastName(lastName);
//    	userService.saveUser(tempUser);
//    }
    
//    @RequestMapping(value = "/user/{username}", method = RequestMethod.POST)
//    public User updateUser(@PathVariable("username") String username, @RequestBody User user) {
//    	List<UserRole> userRoles = new ArrayList();
//		userRoles = userRoleDao.findByUser(username);
//    	
//    	User tempUser = userService.findByUsername(username);
//    	tempUser.setFirstName(user.getFirstName());
//    	tempUser.setLastName(user.getLastName());
//    	return userService.updateUser(tempUser, userRoles);
//    }
    
//    @RequestMapping(value = "/user/{username}", method = RequestMethod.POST)
//    public User updateUser(@PathVariable("username") String username, @RequestBody User user) {
//    	User tempUser = userService.findByUsername(username);
//    	tempUser.setFirstName(user.getFirstName());
//    	
//    	return user
//    }
    
    
}
