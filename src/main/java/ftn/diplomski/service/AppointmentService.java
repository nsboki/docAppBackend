/**
 * 
 */
package ftn.diplomski.service;

import java.util.Date;
import java.util.List;

import ftn.diplomski.entity.Appointment;

/**
 * @author Boki on Dec 26, 2017
 *
 */
public interface AppointmentService {

	/**
	 * @param appointment
	 */
	Appointment createAppointment(Appointment appointment);
	
	List<Appointment> findAll();
	
	Appointment findAppointment(Long id);
	
	void confirmAppointment(Long id);

	/**
	 * @param id
	 * @return
	 */
	List<Appointment> findDoctorAppointments(String username);
	
	List<Appointment> findUserAppointments(Long id);

	/**
	 * @param date
	 * @return
	 */
	List<Appointment> findByDate(Date date);

}
