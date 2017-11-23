package com.emhc.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the usersessions database table.
 * 
 */
@Entity
@Table(name="usersessions")
@NamedQuery(name="Usersession.findAll", query="SELECT u FROM Usersession u")
public class Usersession implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	//bi-directional many-to-one association to Session
	@ManyToOne
	@JoinColumn(name="sessionid")
	private Session session;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="userid")
	private EmhcUser user;

	public Usersession() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Session getSession() {
		return this.session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public EmhcUser getUser() {
		return this.user;
	}

	public void setUser(EmhcUser user) {
		this.user = user;
	}

}