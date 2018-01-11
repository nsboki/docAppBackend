/**
 * 
 */
package ftn.diplomski.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * @author Boki on Dec 14, 2017
 *
 */

@Entity
public class Appointment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO	)
	private Long id;
	private Date date;
	private String description;
	private boolean confirmed;
	
	private String doctorUsername;
	
	public String getDoctorUsername() {
		return doctorUsername;
	}
	public void setDoctorUsername(String doctorUsername) {
		this.doctorUsername = doctorUsername;
	}
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User patient;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public boolean isConfirmed() {
		return confirmed;
	}
	public void setConfirmed(boolean confirmed) {
		this.confirmed = confirmed;
	}
	public User getPatient() {
		return patient;
	}
	public void setPatient(User patient) {
		this.patient = patient;
	}
	
	

}
