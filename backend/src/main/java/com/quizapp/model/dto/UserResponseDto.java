package com.quizapp.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto {
    private Long id;
    
    @NotNull(message = "Question ID is required")
    private Long questionId;
    
    @NotNull(message = "Selected option ID is required")
    private Long selectedOptionId;
}