package com.quizapp.repository;

import com.quizapp.model.entity.Option;
import com.quizapp.model.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OptionRepository extends JpaRepository<Option, Long> {
    List<Option> findByQuestion(Question question);
    List<Option> findByQuestionAndCorrect(Question question, boolean correct);
}