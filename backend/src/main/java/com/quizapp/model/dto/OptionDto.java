package com.quizapp.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OptionDto {
    private Long id;
    
    @NotBlank(message = "Option text is required")
    private String optionText;
    
    @NotNull(message = "Correct flag is required")
    private Boolean isCorrect;
}