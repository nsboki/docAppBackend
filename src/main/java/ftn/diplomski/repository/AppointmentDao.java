/**
 * 
 */
package ftn.diplomski.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ftn.diplomski.entity.Appointment;

/**
 * @author Boki on Dec 26, 2017
 *
 */
public interface AppointmentDao extends CrudRepository<Appointment, Long>{

	List<Appointment> findAll();
}
