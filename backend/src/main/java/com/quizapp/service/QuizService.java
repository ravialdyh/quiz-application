package com.quizapp.service;

import com.quizapp.model.dto.QuizDto;
import com.quizapp.model.entity.Quiz;

import java.util.List;

public interface QuizService {
    Quiz createQuiz(QuizDto quizDto, Long userId);
    Quiz findById(Long id);
    List<Quiz> findAllQuizzes();
    List<Quiz> findQuizzesByUser(Long userId);
    Quiz updateQuiz(Long id, QuizDto quizDto);
    void deleteQuiz(Long id);
}