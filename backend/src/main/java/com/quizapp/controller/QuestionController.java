package com.quizapp.controller;

import com.quizapp.model.dto.QuestionDto;
import com.quizapp.model.entity.Question;
import com.quizapp.service.QuestionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/questions")
public class QuestionController {

    private final QuestionService questionService;

    @Autowired
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @PostMapping("/quiz/{quizId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Question> createQuestion(@PathVariable Long quizId,
                                                   @Valid @RequestBody QuestionDto questionDto) {
        Question question = questionService.createQuestion(quizId, questionDto);
        return ResponseEntity.ok(question);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Question> getQuestionById(@PathVariable Long id) {
        Question question = questionService.findById(id);
        return ResponseEntity.ok(question);
    }

    @GetMapping("/quiz/{quizId}")
    public ResponseEntity<List<Question>> getQuestionsByQuiz(@PathVariable Long quizId) {
        List<Question> questions = questionService.findQuestionsByQuiz(quizId);
        return ResponseEntity.ok(questions);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Question> updateQuestion(@PathVariable Long id,
                                                   @Valid @RequestBody QuestionDto questionDto) {
        Question updatedQuestion = questionService.updateQuestion(id, questionDto);
        return ResponseEntity.ok(updatedQuestion);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteQuestion(@PathVariable Long id) {
        questionService.deleteQuestion(id);
        return ResponseEntity.ok("Question deleted successfully");
    }
}
