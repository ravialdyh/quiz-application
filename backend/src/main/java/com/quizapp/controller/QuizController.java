package com.quizapp.controller;

import com.quizapp.model.dto.QuizDto;
import com.quizapp.model.entity.Quiz;
import com.quizapp.security.services.UserDetailsImpl;
import com.quizapp.service.QuizService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quizzes")
public class QuizController {
    private final QuizService quizService;

    @Autowired
    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Quiz> createQuiz(
            @Valid @RequestBody QuizDto quizDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Quiz quiz = quizService.createQuiz(quizDto, userDetails.getId());
        return ResponseEntity.ok(quiz);
    }

    @GetMapping
    public ResponseEntity<List<Quiz>> getAllQuizzes() {
        List<Quiz> quizzes = quizService.findAllQuizzes();
        return ResponseEntity.ok(quizzes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Quiz> getQuizById(@PathVariable Long id) {
        Quiz quiz = quizService.findById(id);
        return ResponseEntity.ok(quiz);
    }

    @GetMapping("/user/{userId}")
    @PreAuthorize("hasRole('ADMIN') or authentication.principal.id == #userId")
    public ResponseEntity<List<Quiz>> getQuizzesByUser(@PathVariable Long userId) {
        List<Quiz> quizzes = quizService.findQuizzesByUser(userId);
        return ResponseEntity.ok(quizzes);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Quiz> updateQuiz(
            @PathVariable Long id,
            @Valid @RequestBody QuizDto quizDto) {
        Quiz updatedQuiz = quizService.updateQuiz(id, quizDto);
        return ResponseEntity.ok(updatedQuiz);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteQuiz(@PathVariable Long id) {
        quizService.deleteQuiz(id);
        return ResponseEntity.ok("Quiz deleted successfully");
    }
}