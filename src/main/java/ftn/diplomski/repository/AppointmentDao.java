/**
 * 
 */
package ftn.diplomski.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ftn.diplomski.entity.Appointment;
import ftn.diplomski.entity.User;

/**
 * @author Boki on Dec 26, 2017
 *
 */
public interface AppointmentDao extends CrudRepository<Appointment, Long>{

	List<Appointment> findAll();

	/**
	 * @param id
	 * @return
	 */
	List<Appointment> findByDoctorUsername(String username);
	
	List<Appointment> findByPatient(User user);

	/**
	 * @param date
	 * @return
	 */
	List<Appointment> findByDate(Date date);

	/**
	 * @param date
	 * @param doctorUsername
	 * @return
	 */
	List<Appointment> findByDateAndDoctorUsername(Date date, String doctorUsername);
}
