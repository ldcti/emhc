package com.emhc.dto;

import java.util.Date;
import java.util.List;

import com.emhc.model.EmhcUser;
import com.emhc.model.Program;
import com.emhc.model.Role;

public class StudentProfileUpdate {
	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public int getCreatedby() {
		return createdby;
	}

	public void setCreatedby(int createdby) {
		this.createdby = createdby;
	}

	public Date getCreationdate() {
		return creationdate;
	}

	public void setCreationdate(Date creationdate) {
		this.creationdate = creationdate;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getProgramyear() {
		return programyear;
	}

	public void setProgramyear(int programyear) {
		this.programyear = programyear;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public byte getViewer() {
		return viewer;
	}

	public void setViewer(byte viewer) {
		this.viewer = viewer;
	}

	public Program getProgram() {
		return program;
	}

	public void setProgram(Program program) {
		this.program = program;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public List<Program> getPrograms() {
		return programs;
	}

	public void setPrograms(List<Program> programs) {
		this.programs = programs;
	}

	private int userid;
	private int createdby;
	private Date creationdate;
	private String firstname;
	private String lastname;
	private String email;
	private String password;
	private int programyear;
	private String status;
	private String username;
	private byte viewer;
	private Program program;
	private Role role;
	private List<Program> programs;

	public StudentProfileUpdate() {

	}

	public StudentProfileUpdate(EmhcUser user) {
		this.userid = user.getUserid();
		this.username = user.getUsername();
		this.firstname = user.getFirstname();
		this.lastname = user.getLastname();
		this.email = user.getEmail();
		this.password = user.getPassword();
		this.program = user.getProgram();
		this.createdby = user.getCreatedby();
		this.creationdate = user.getCreationdate();
		this.programyear = user.getProgramyear();
		this.role = user.getRole();
		this.status = user.getStatus();
		this.viewer = user.getViewer();

	}

	public EmhcUser getUser() {
		EmhcUser user = new EmhcUser();
		user.setUserid(this.userid);
		user.setUsername(this.username);
		user.setFirstname(this.firstname);
		user.setLastname(this.lastname);
		user.setEmail(this.email);
		user.setPassword(this.password);
		user.setProgram(this.program);
		user.setCreatedby(this.createdby);
		user.setCreationdate(this.creationdate);
		user.setProgramyear(this.programyear);
		user.setRole(this.role);
		user.setStatus(this.status);
		user.setViewer(this.viewer);
		return user;
	}

}
