package com.emhc.dto;

import java.util.List;

import com.emhc.model.EmhcUser;
import com.emhc.model.Session;

public class StudentScheduleTest {
	private int userid;
	private Session session;
	private List<Session> sessions;
	
	public StudentScheduleTest(){
		
	}
	public StudentScheduleTest(EmhcUser user){
		this.userid = user.getUserid();
		this.sessions = user.getProgram().getSessions();
		
	}

	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
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

}
