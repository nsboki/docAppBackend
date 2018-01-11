/**
 * 
 */
package ftn.diplomski.resource;

import static org.mockito.Mockito.RETURNS_DEEP_STUBS;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ftn.diplomski.entity.Appointment;
import ftn.diplomski.entity.User;
import ftn.diplomski.service.AppointmentService;
import ftn.diplomski.service.UserService;

/**
 * @author Boki on Jan 1, 2018
 *
 */

@RestController
@RequestMapping("api/appointment")
//@PreAuthorize("hasRole('ADMIN')")
public class AppointmentResource {
	
	@Autowired
	private AppointmentService appointmentService;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/all")
	public List<Appointment> findAppointmentList() {
		List<Appointment> appointmentList = appointmentService.findAll();
		
		return appointmentList;
	}
	
	@RequestMapping("/{username}")
	public List<Appointment> findDoctorAppointmentList(@PathVariable("username")String username) {
		List<Appointment> appList = appointmentService.findDoctorAppointments(username);
		return appList;
	}
	
	@RequestMapping("/{id}")
	public List<Appointment> findUserAppointmentList(@PathVariable("id")Long id) {
		List<Appointment> appList = appointmentService.findUserAppointments(id);
		return appList;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.POST)
	public Appointment createAppointment(@PathVariable("id")Long id, @RequestBody Appointment appointment) {
		User app4User = userService.findById(id);
		Appointment newAppointment = new Appointment();
		newAppointment.setPatient(app4User);
		newAppointment.setConfirmed(false);
		newAppointment.setDate(appointment.getDate());
		newAppointment.setDescription(appointment.getDescription());
		newAppointment.setDoctorUsername(appointment.getDoctorUsername());
		return appointmentService.createAppointment(newAppointment);
	}
	
	@RequestMapping("/{id}/confirm")
	public void confirmAppointment(@PathVariable("id") Long id) {
		appointmentService.confirmAppointment(id);
	}

}
