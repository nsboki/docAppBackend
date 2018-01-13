/**
 * 
 */
package ftn.diplomski.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.diplomski.entity.Appointment;
import ftn.diplomski.repository.AppointmentDao;
import ftn.diplomski.repository.UserDao;

/**
 * @author Boki on Dec 26, 2017
 *
 */
@Service
public class AppointmentServiceImpl implements AppointmentService {

	@Autowired
	private AppointmentDao appointmentDao;
	
	@Autowired
	private UserDao userDao;
	
	@Override
	public Appointment createAppointment(Appointment appointment) {
		return appointmentDao.save(appointment);
	}

	@Override
	public List<Appointment> findAll() {
		return appointmentDao.findAll();
	}

	@Override
	public Appointment findAppointment(Long id) {
		return appointmentDao.findOne(id);
	}

	@Override
	public void confirmAppointment(Long id) {
		Appointment appointment = findAppointment(id);
		appointment.setConfirmed(true);
		appointmentDao.save(appointment);
	}

	@Override
	public List<Appointment> findDoctorAppointments(String username) {
		return appointmentDao.findByDoctorUsername(username);
	}

	@Override
	public List<Appointment> findUserAppointments(Long id) {
		return appointmentDao.findByPatient(userDao.findOne(id));
	}

	@Override
	public List<Appointment> findByDate(Date date) {
		return appointmentDao.findByDate(date);
	}

	@Override
	public Appointment updateAppointment(Appointment appointment) {
		return appointmentDao.save(appointment);
	}

	@Override
	public void deleteAppointment(Appointment appointment) {
		appointmentDao.delete(appointment);
	}
	
	

}
