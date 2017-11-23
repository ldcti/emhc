package com.emhc.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Time;
import java.util.List;


/**
 * The persistent class for the schedules database table.
 * 
 */
@Entity
@Table(name="schedules")
@NamedQuery(name="Schedule.findAll", query="SELECT s FROM Schedule s")
public class Schedule implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int scheduleid;

	private int capacity;

	private int duration;

	private Time scheduletime;

	//bi-directional many-to-one association to Registration
	@OneToMany(mappedBy="schedule")
	private List<Registration> registrations;

	//bi-directional many-to-one association to Session
	@ManyToOne
	@JoinColumn(name="sessionid")
	private Session session;

	public Schedule() {
	}

	public int getScheduleid() {
		return this.scheduleid;
	}

	public void setScheduleid(int scheduleid) {
		this.scheduleid = scheduleid;
	}

	public int getCapacity() {
		return this.capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public int getDuration() {
		return this.duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public Time getScheduletime() {
		return this.scheduletime;
	}

	public void setScheduletime(Time scheduletime) {
		this.scheduletime = scheduletime;
	}

	public List<Registration> getRegistrations() {
		return this.registrations;
	}

	public void setRegistrations(List<Registration> registrations) {
		this.registrations = registrations;
	}

	public Registration addRegistration(Registration registration) {
		getRegistrations().add(registration);
		registration.setSchedule(this);

		return registration;
	}

	public Registration removeRegistration(Registration registration) {
		getRegistrations().remove(registration);
		registration.setSchedule(null);

		return registration;
	}

	public Session getSession() {
		return this.session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

}