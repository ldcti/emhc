package com.emhc.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

/**
 * The persistent class for the registrations database table.
 * 
 */
@Entity
@Table(name = "registrations")
@NamedQuery(name = "Registration.findAll", query = "SELECT r FROM Registration r")
public class Registration implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int registrationid;

	private String feedback;

	@Temporal(TemporalType.DATE)
	private Date registdate;

	private byte reviewed;

	// bi-directional many-to-one association to Payment
	@ManyToOne
	@JoinColumn(name = "paymentid")
	private Payment payment;

	// bi-directional many-to-one association to Schedule
	@ManyToOne
	@JoinColumn(name = "scheduleid")
	private Schedule schedule;

	// bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name = "userid")
	private EmhcUser user;

	public Registration() {
	}

	public int getRegistrationid() {
		return this.registrationid;
	}

	public void setRegistrationid(int registrationid) {
		this.registrationid = registrationid;
	}

	public String getFeedback() {
		return this.feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	public Date getRegistdate() {
		return this.registdate;
	}

	public void setRegistdate(Date registdate) {
		this.registdate = registdate;
	}

	public byte getReviewed() {
		return this.reviewed;
	}

	public void setReviewed(byte reviewed) {
		this.reviewed = reviewed;
	}

	public Payment getPayment() {
		return this.payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public Schedule getSchedule() {
		return this.schedule;
	}

	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}

	public EmhcUser getUser() {
		return this.user;
	}

	public void setUser(EmhcUser user) {
		this.user = user;
	}

}