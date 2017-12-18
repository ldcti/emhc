package com.emhc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.emhc.model.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

	Question findByQuestionid(int id);

	List<Question> findAll();

	List<Question> findByName(String name);
}
