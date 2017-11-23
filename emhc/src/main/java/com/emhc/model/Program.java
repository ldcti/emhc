package com.emhc.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the programs database table.
 * 
 */
@Entity
@Table(name="programs")
@NamedQuery(name="Program.findAll", query="SELECT p FROM Program p")
public class Program implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int programid;

	private String name;

	//bi-directional many-to-one association to Organization
	@ManyToOne
	@JoinColumn(name="organizationid")
	private Organization organization;

	//bi-directional many-to-one association to Session
	@OneToMany(mappedBy="program")
	private List<Session> sessions;

	//bi-directional many-to-one association to User
	@OneToMany(mappedBy="program")
	private List<EmhcUser> users;

	public Program() {
	}

	public int getProgramid() {
		return this.programid;
	}

	public void setProgramid(int programid) {
		this.programid = programid;
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
		session.setProgram(this);

		return session;
	}

	public Session removeSession(Session session) {
		getSessions().remove(session);
		session.setProgram(null);

		return session;
	}

	public List<EmhcUser> getUsers() {
		return this.users;
	}

	public void setUsers(List<EmhcUser> users) {
		this.users = users;
	}

	public EmhcUser addUser(EmhcUser user) {
		getUsers().add(user);
		user.setProgram(this);

		return user;
	}

	public EmhcUser removeUser(EmhcUser user) {
		getUsers().remove(user);
		user.setProgram(null);

		return user;
	}

}