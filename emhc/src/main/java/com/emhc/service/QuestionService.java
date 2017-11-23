package com.emhc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emhc.model.Question;
import com.emhc.repository.QuestionRepository;


@Service
public class QuestionService {

	@Autowired
	private QuestionRepository questionRepository;	
	
	public Question save(Question question) {
		return questionRepository.save(question);
	}
}
