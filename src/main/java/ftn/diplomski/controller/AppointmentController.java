/**
 * 
 */
package ftn.diplomski.controller;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ftn.diplomski.entity.Appointment;
import ftn.diplomski.entity.User;
import ftn.diplomski.service.AppointmentService;
import ftn.diplomski.service.UserService;

/**
 * @author Boki on Dec 26, 2017
 *
 */
@Controller
@RequestMapping("/appointment")
public class AppointmentController {
	
	@Autowired
	private AppointmentService appointmentService;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String createAppointment(Model model) {
		Appointment appointment = new Appointment();
		model.addAttribute("appointment", appointment);
		model.addAttribute("dateString", ""); 
		
		return "appointment";
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String createAppointmentPost(@ModelAttribute("appointment") Appointment appointment, @ModelAttribute("dateString") String date, Model model, Principal principal) throws ParseException {
		
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		Date d1;
		try {
			d1 = format1.parse(date);
			appointment.setDate(d1);
		} catch (java.text.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		User user = userService.findByUsername(principal.getName());
		appointment.setPatient(user);
		
		appointmentService.createAppointment(appointment);
		
		return "redirect:/userFront";
	}

}
