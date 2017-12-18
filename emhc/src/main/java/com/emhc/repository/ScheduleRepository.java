package com.emhc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.emhc.model.Schedule;
import com.emhc.model.Session;

public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {
	Schedule findByScheduleid(int id);

	public List<Schedule> findBySession(Session session);
}
