package com.quizapp.service;

import com.quizapp.model.dto.UserResponseDto;
import com.quizapp.model.entity.QuizAttempt;

import java.util.List;

public interface QuizAttemptService {
    QuizAttempt startQuizAttempt(Long quizId, Long userId);
    QuizAttempt findById(Long id);
    List<QuizAttempt> findAttemptsByUser(Long userId);
    List<QuizAttempt> findAttemptsByQuiz(Long quizId);
    QuizAttempt submitQuizAttempt(Long attemptId, List<UserResponseDto> responses);
    void deleteQuizAttempt(Long id);
}