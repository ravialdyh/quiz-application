package com.quizapp.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuizAttemptDto {
    private Long id;
    private Long quizId;
    private String quizTitle;
    private BigDecimal score;
    private LocalDateTime attemptDate;
    private List<UserResponseDto> responses = new ArrayList<>();
}