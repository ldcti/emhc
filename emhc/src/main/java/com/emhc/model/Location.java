package com.emhc.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the locations database table.
 * 
 */
@Entity
@Table(name="locations")
@NamedQuery(name="Location.findAll", query="SELECT l FROM Location l")
public class Location implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int locationid;

	private String name;

	//bi-directional many-to-one association to Organization
	@ManyToOne
	@JoinColumn(name="organizationid")
	private Organization organization;

	//bi-directional many-to-one association to Session
	@OneToMany(mappedBy="location")
	private List<Session> sessions;

	public Location() {
	}

	public int getLocationid() {
		return this.locationid;
	}

	public void setLocationid(int locationid) {
		this.locationid = locationid;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Organization getOrganization() {
		return this.organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public List<Session> getSessions() {
		return this.sessions;
	}

	public void setSessions(List<Session> sessions) {
		this.sessions = sessions;
	}

	public Session addSession(Session session) {
		getSessions().add(session);
		session.setLocation(this);

		return session;
	}

	public Session removeSession(Session session) {
		getSessions().remove(session);
		session.setLocation(null);

		return session;
	}

}