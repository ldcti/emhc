package com.emhc.dto;

import java.util.List;

import com.emhc.model.EmhcUser;
import com.emhc.model.Schedule;
import com.emhc.model.Session;

public class StudentScheduleTest {
	private int registrationid;
	private int userid;
	private Session session;
	private int scheduleid;
	private List<Session> sessions;
	private List<Schedule> schedules;

	public StudentScheduleTest(){
		
	}
	public StudentScheduleTest(EmhcUser user){
		this.userid = user.getUserid();
		this.sessions = user.getProgram().getSessions();
		
	}

	public int getRegistrationid() {
		return registrationid;
	}
	public void setRegistrationid(int registrationid) {
		this.registrationid = registrationid;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public int getScheduleid() {
		return scheduleid;
	}
	public void setScheduleid(int scheduleid) {
		this.scheduleid = scheduleid;
	}
	public Session getSession() {
		return session;
	}
	public void setSession(Session session) {
		this.session = session;
	}
	public List<Session> getSessions() {
		return sessions;
	}
	public void setSessions(List<Session> sessions) {
		this.sessions = sessions;
	}
	public List<Schedule> getSchedules() {
		return schedules;
	}
	public void setSchedules(List<Schedule> schedules) {
		this.schedules = schedules;
	}

}
