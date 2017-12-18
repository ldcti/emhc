package com.emhc.service;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.stereotype.Service;

import com.emhc.model.Schedule;
import com.emhc.model.Session;
import com.emhc.repository.ScheduleRepository;

@Service("ScheduleService")
public class ScheduleService {

	@Autowired
	private ScheduleRepository ScheduleRepository;

	public Schedule findByScheduleid(int id) {
		return ScheduleRepository.findByScheduleid(id);
	}

	public List<Schedule> findAll() {
		return ScheduleRepository.findAll();
	}

	public List<Schedule> getBySession(Session ses) {
		return ScheduleRepository.findBySession(ses);
	}

	public Schedule saveSchedule(Schedule Schedule) {
		return ScheduleRepository.save(Schedule);
	}

	public String print(Schedule object, Locale locale) {
		return (object != null ? Integer.toString(object.getScheduleid()) : "");
	}

	public Schedule parse(String text, Locale locale) throws ParseException {
		Integer id = Integer.valueOf(text);
		return ScheduleRepository.findByScheduleid(id);
	}

}
