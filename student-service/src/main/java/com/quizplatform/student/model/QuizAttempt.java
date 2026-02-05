package com.quizplatform.student.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "quiz_attempts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuizAttempt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long studentId;

    @Column(nullable = false)
    private Long quizId;

    @Column(nullable = false)
    private Double score;

    @Column(nullable = false)
    private LocalDateTime attemptDate;

    private Integer timeTaken; // in seconds

    @PrePersist
    protected void onCreate() {
        attemptDate = LocalDateTime.now();
    }
}
