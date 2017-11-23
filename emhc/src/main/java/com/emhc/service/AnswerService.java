package com.emhc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emhc.model.Answer;
import com.emhc.repository.AnswerRepository;

@Service("answerService")
public class AnswerService {
	@Autowired
	private AnswerRepository answerRepository;
	
	public Answer findAnswerByAnswerid(int answerid){
		return answerRepository.findByAnswerid(answerid);
	}
	public Answer saveAnswer(Answer answer){
		return answerRepository.save(answer);
	}
}
