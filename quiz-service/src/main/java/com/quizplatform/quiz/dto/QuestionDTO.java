package com.quizplatform.quiz.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionDTO {

    private Long id;

    @NotBlank(message = "Question text is required")
    private String questionText;

    @NotNull(message = "Options are required")
    private List<String> options;

    @NotBlank(message = "Correct answer is required")
    private String correctAnswer;

    @NotNull(message = "Points are required")
    @Positive(message = "Points must be positive")
    private Integer points;
}
