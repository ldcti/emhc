package com.emhc.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the functions database table.
 * 
 */
@Entity
@Table(name="functions")
@NamedQuery(name="Function.findAll", query="SELECT f FROM Function f")
public class Function implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int functionid;

	private String link;

	private String name;

	//bi-directional many-to-one association to Funcrole
	@OneToMany(mappedBy="function")
	private List<Funcrole> funcroles;

	//bi-directional many-to-one association to Function
	@ManyToOne
	@JoinColumn(name="parent_id")
	private Function function;

	//bi-directional many-to-one association to Function
	@OneToMany(mappedBy="function")
	private List<Function> functions;

	public Function() {
	}

	public int getFunctionid() {
		return this.functionid;
	}

	public void setFunctionid(int functionid) {
		this.functionid = functionid;
	}

	public String getLink() {
		return this.link;
	}

	public void setLink(String link) {
		this.link = link;
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
		funcrole.setFunction(this);

		return funcrole;
	}

	public Funcrole removeFuncrole(Funcrole funcrole) {
		getFuncroles().remove(funcrole);
		funcrole.setFunction(null);

		return funcrole;
	}

	public Function getFunction() {
		return this.function;
	}

	public void setFunction(Function function) {
		this.function = function;
	}

	public List<Function> getFunctions() {
		return this.functions;
	}

	public void setFunctions(List<Function> functions) {
		this.functions = functions;
	}

	public Function addFunction(Function function) {
		getFunctions().add(function);
		function.setFunction(this);

		return function;
	}

	public Function removeFunction(Function function) {
		getFunctions().remove(function);
		function.setFunction(null);

		return function;
	}

}