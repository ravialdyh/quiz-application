package com.quizapp.controller;

import com.quizapp.model.dto.QuizStatisticsDto;
import com.quizapp.model.dto.UserStatisticsDto;
import com.quizapp.service.AnalyticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/analytics")
public class AnalyticsController {

    private final AnalyticsService analyticsService;

    @Autowired
    public AnalyticsController(AnalyticsService analyticsService) {
        this.analyticsService = analyticsService;
    }

    @GetMapping("/quiz/{quizId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<QuizStatisticsDto> getQuizStatistics(@PathVariable Long quizId) {
        QuizStatisticsDto stats = analyticsService.getQuizStatistics(quizId);
        return ResponseEntity.ok(stats);
    }

    @GetMapping("/user/{userId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserStatisticsDto> getUserStatistics(@PathVariable Long userId) {
        UserStatisticsDto stats = analyticsService.getUserStatistics(userId);
        return ResponseEntity.ok(stats);
    }
}
