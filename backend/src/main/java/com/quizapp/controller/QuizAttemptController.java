package com.quizapp.controller;

import com.quizapp.model.dto.UserResponseDto;
import com.quizapp.model.entity.QuizAttempt;
import com.quizapp.service.QuizAttemptService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/attempts")
public class QuizAttemptController {

    private final QuizAttemptService quizAttemptService;

    @Autowired
    public QuizAttemptController(QuizAttemptService quizAttemptService) {
        this.quizAttemptService = quizAttemptService;
    }

    @PostMapping("/start/{quizId}")
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<QuizAttempt> startQuizAttempt(@PathVariable Long quizId,
                                                        @RequestParam Long userId) {
        // In a real-world application, userId should be extracted from the security context.
        QuizAttempt attempt = quizAttemptService.startQuizAttempt(quizId, userId);
        return ResponseEntity.ok(attempt);
    }

    @PostMapping("/submit/{attemptId}")
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<QuizAttempt> submitQuizAttempt(@PathVariable Long attemptId,
                                                         @Valid @RequestBody List<UserResponseDto> responses) {
        QuizAttempt submittedAttempt = quizAttemptService.submitQuizAttempt(attemptId, responses);
        return ResponseEntity.ok(submittedAttempt);
    }

    @GetMapping("/user/{userId}")
    @PreAuthorize("hasRole('ADMIN') or (hasRole('STUDENT') and #userId == principal.id)")
    public ResponseEntity<List<QuizAttempt>> getAttemptsByUser(@PathVariable Long userId) {
        List<QuizAttempt> attempts = quizAttemptService.findAttemptsByUser(userId);
        return ResponseEntity.ok(attempts);
    }
}
