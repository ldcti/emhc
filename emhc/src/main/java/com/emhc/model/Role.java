package com.emhc.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the roles database table.
 * 
 */
@Entity
@Table(name="roles")
@NamedQuery(name="Role.findAll", query="SELECT r FROM Role r")
public class Role implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int roleid;

	private String name;

	//bi-directional many-to-one association to Funcrole
	@OneToMany(mappedBy="role")
	private List<Funcrole> funcroles;

	//bi-directional many-to-one association to User
	@OneToMany(mappedBy="role")
	private List<EmhcUser> users;

	public Role() {
	}

	public int getRoleid() {
		return this.roleid;
	}

	public void setRoleid(int roleid) {
		this.roleid = roleid;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Funcrole> getFuncroles() {
		return this.funcroles;
	}

	public void setFuncroles(List<Funcrole> funcroles) {
		this.funcroles = funcroles;
	}

	public Funcrole addFuncrole(Funcrole funcrole) {
		getFuncroles().add(funcrole);
		funcrole.setRole(this);

		return funcrole;
	}

	public Funcrole removeFuncrole(Funcrole funcrole) {
		getFuncroles().remove(funcrole);
		funcrole.setRole(null);

		return funcrole;
	}

	public List<EmhcUser> getUsers() {
		return this.users;
	}

	public void setUsers(List<EmhcUser> users) {
		this.users = users;
	}

	public EmhcUser addUser(EmhcUser user) {
		getUsers().add(user);
		user.setRole(this);

		return user;
	}

	public EmhcUser removeUser(EmhcUser user) {
		getUsers().remove(user);
		user.setRole(null);

		return user;
	}

}