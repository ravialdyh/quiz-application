package com.quizapp.service;

import com.quizapp.model.dto.QuizStatisticsDto;
import com.quizapp.model.dto.UserStatisticsDto;

public interface AnalyticsService {
    QuizStatisticsDto getQuizStatistics(Long quizId);
    UserStatisticsDto getUserStatistics(Long userId);
}