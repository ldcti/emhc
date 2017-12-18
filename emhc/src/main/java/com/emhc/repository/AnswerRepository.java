package com.emhc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.emhc.model.Answer;

public interface AnswerRepository extends JpaRepository<Answer, Integer> {
	Answer findByAnswerid(int answerid);
	// Answer saveAnswerByUserid(int userid);
}
