package com.quizapp.repository;

import com.quizapp.model.entity.Quiz;
import com.quizapp.model.entity.QuizAttempt;
import com.quizapp.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizAttemptRepository extends JpaRepository<QuizAttempt, Long> {
    List<QuizAttempt> findByUser(User user);
    List<QuizAttempt> findByQuiz(Quiz quiz);
    List<QuizAttempt> findByUserAndQuiz(User user, Quiz quiz);
}