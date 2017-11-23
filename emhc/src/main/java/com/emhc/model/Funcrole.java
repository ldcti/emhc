package com.emhc.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the funcroles database table.
 * 
 */
@Entity
@Table(name="funcroles")
@NamedQuery(name="Funcrole.findAll", query="SELECT f FROM Funcrole f")
public class Funcrole implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int funcroleid;

	//bi-directional many-to-one association to Function
	@ManyToOne
	@JoinColumn(name="functionid")
	private Function function;

	//bi-directional many-to-one association to Role
	@ManyToOne
	@JoinColumn(name="roleid")
	private Role role;

	public Funcrole() {
	}

	public int getFuncroleid() {
		return this.funcroleid;
	}

	public void setFuncroleid(int funcroleid) {
		this.funcroleid = funcroleid;
	}

	public Function getFunction() {
		return this.function;
	}

	public void setFunction(Function function) {
		this.function = function;
	}

	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}