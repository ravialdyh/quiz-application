package com.quizapp.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserStatisticsDto {
    private Long userId;
    private String username;
    private int totalAttempts;
    private double averageScore;
    private double highestScore;
    private double lowestScore;
}
