package com.quizapp.service;

import com.quizapp.model.dto.QuestionDto;
import com.quizapp.model.entity.Question;

import java.util.List;

public interface QuestionService {
    Question createQuestion(Long quizId, QuestionDto questionDto);
    Question findById(Long id);
    List<Question> findQuestionsByQuiz(Long quizId);
    Question updateQuestion(Long id, QuestionDto questionDto);
    void deleteQuestion(Long id);
}