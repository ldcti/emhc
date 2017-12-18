package com.emhc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emhc.model.Program;
import com.emhc.model.Session;
import com.emhc.repository.SessionRepository;

@Service("sessionService")
public class SessionService {

	@Autowired
	private SessionRepository sessionRepository;

	public Session findById(int sessionid) {
		return sessionRepository.findBySessionid(sessionid);
	}

	public List<Session> findByProgram(Program program) {
		return sessionRepository.findByProgram(program);
	}

	public List<Session> findAll() {
		System.out.println("------run in Service-------");
		return sessionRepository.findAll();
	}

	public Session saveSession(Session session) {
		return sessionRepository.save(session);
	}

}
