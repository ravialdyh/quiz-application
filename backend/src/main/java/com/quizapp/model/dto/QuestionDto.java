package com.quizapp.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionDto {
    private Long id;
    
    @NotBlank(message = "Question text is required")
    private String questionText;
    
    @Size(min = 4, max = 4, message = "Multiple choice questions must have exactly 4 options")
    private List<OptionDto> options = new ArrayList<>();
}