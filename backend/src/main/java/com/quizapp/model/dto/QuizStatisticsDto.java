package com.quizapp.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuizStatisticsDto {
    private Long quizId;
    private String quizTitle;
    private int totalAttempts;
    private double averageScore;
    private double highestScore;
    private double lowestScore;
}
