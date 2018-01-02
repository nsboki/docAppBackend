/**
 * 
 */
package ftn.diplomski.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ftn.diplomski.entity.Appointment;
import ftn.diplomski.service.AppointmentService;

/**
 * @author Boki on Jan 1, 2018
 *
 */

@RestController
@RequestMapping("api/appointment")
@PreAuthorize("hasRole('ADMIN')")
public class AppointmentResource {
	
	@Autowired
	private AppointmentService appointmentService;
	
	@RequestMapping("/all")
	public List<Appointment> findAppointmentList() {
		List<Appointment> appointmentList = appointmentService.findAll();
		
		return appointmentList;
	}
	
	@RequestMapping("/{id}/confirm")
	public void confirmAppointment(@PathVariable("id") Long id) {
		appointmentService.confirmAppointment(id);
	}

}