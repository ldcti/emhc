package com.emhc.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

/**
 * The persistent class for the organizations database table.
 * 
 */
@Entity
@Table(name = "organizations")
@NamedQuery(name = "Organization.findAll", query = "SELECT o FROM Organization o")
public class Organization implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "organizationid", updatable = false, nullable = false)
	private long organizationid;

	private String contact;

	private String domain;

	private String email;

	private String name;

	private String phone;

	// bi-directional many-to-one association to Location
	@OneToMany(mappedBy = "organization")
	private List<Location> locations;

	// bi-directional many-to-one association to Program
	@OneToMany(mappedBy = "organization")
	private List<Program> programs;

	public Organization() {
	}

	public long getOrganizationid() {
		return this.organizationid;
	}

	public void setOrganizationid(long organizationid) {
		this.organizationid = organizationid;
	}

	public String getContact() {
		return this.contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getDomain() {
		return this.domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public List<Location> getLocations() {
		return this.locations;
	}

	public void setLocations(List<Location> locations) {
		this.locations = locations;
	}

	public Location addLocation(Location location) {
		getLocations().add(location);
		location.setOrganization(this);

		return location;
	}

	public Location removeLocation(Location location) {
		getLocations().remove(location);
		location.setOrganization(null);

		return location;
	}

	public List<Program> getPrograms() {
		return this.programs;
	}

	public void setPrograms(List<Program> programs) {
		this.programs = programs;
	}

	public Program addProgram(Program program) {
		getPrograms().add(program);
		program.setOrganization(this);

		return program;
	}

	public Program removeProgram(Program program) {
		getPrograms().remove(program);
		program.setOrganization(null);

		return program;
	}

}