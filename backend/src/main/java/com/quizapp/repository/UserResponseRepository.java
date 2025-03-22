package com.quizapp.repository;

import com.quizapp.model.entity.QuizAttempt;
import com.quizapp.model.entity.UserResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserResponseRepository extends JpaRepository<UserResponse, Long> {
    List<UserResponse> findByQuizAttempt(QuizAttempt quizAttempt);
}