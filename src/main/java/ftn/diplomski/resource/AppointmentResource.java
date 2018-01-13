/**
 * 
 */
package ftn.diplomski.resource;

import static org.mockito.Mockito.RETURNS_DEEP_STUBS;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
	
	//doctor appointments
	
	@RequestMapping("/{username}")
	public List<Appointment> findDoctorAppointmentList(@PathVariable("username")String username) {
		return appointmentService.findDoctorAppointments(username);
	}
	
	//user appointments
	@RequestMapping("/me/{id}")
	public List<Appointment> findUserAppointmentList(@PathVariable("id")Long id) {
		return appointmentService.findUserAppointments(id);
	}
	
	@RequestMapping(value = "/new/{username}/{dateString}", method = RequestMethod.POST)
	public Appointment createAppointment(@PathVariable("username")String username, @RequestBody String dateString) {
		User app4User = userService.findByUsername(username);
		Appointment newAppointment = new Appointment();
		newAppointment.setPatient(app4User);
		newAppointment.setConfirmed(false);
		DateFormat format = new SimpleDateFormat("yyyy-mm-dd");
		Date date = new Date();
		try {
			date = format.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		List<Appointment> appList = appointmentService.findByDateAndDoctor(date,app4User.getDoctorUsername());
		int listSize = appList.size();
		int number;
		if (listSize == 0) {
			number = 1;
		} else {
			number = appList.get((listSize)-1).getNumber()+1;
		}
//		newAppointment.setNumber(appList.size()+1);
		newAppointment.setNumber(number);
		newAppointment.setDate(date);
		newAppointment.setDescription("");
		newAppointment.setDoctorUsername(app4User.getDoctorUsername());
		return appointmentService.createAppointment(newAppointment);
	}
	
	@RequestMapping("/{id}/confirm")
	public void confirmAppointment(@PathVariable("id") Long id) {
		appointmentService.confirmAppointment(id);
	}
	
	@RequestMapping(value = "/description/{id}", method = RequestMethod.POST)
	public Appointment updateDescription(@PathVariable("id")Long id, @RequestBody String description) {
		Appointment appointment = appointmentService.findAppointment(id);
		appointment.setDescription(description);
		return appointmentService.updateAppointment(appointment);
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public void deleteAppointment(@PathVariable("id")Long id) {
		Appointment appointment = appointmentService.findAppointment(id);
		appointmentService.deleteAppointment(appointment);
	}
	
	//"/api/appointment/active/"+username;
	@RequestMapping("/active/{username}")
	public boolean getActiveAppointment(@PathVariable("username") String username) {
		return appointmentService.haveActiveAppointment(username);
	}

}
