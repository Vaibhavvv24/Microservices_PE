package com.quizplatform.student.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuizAttemptDTO {

    private Long id;

    @NotNull(message = "Student ID is required")
    private Long studentId;

    @NotNull(message = "Quiz ID is required")
    private Long quizId;

    @NotNull(message = "Score is required")
    @Positive(message = "Score must be positive")
    private Double score;

    private LocalDateTime attemptDate;

    private Integer timeTaken;
}
