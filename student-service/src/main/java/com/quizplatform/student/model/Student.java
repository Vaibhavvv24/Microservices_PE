package com.quizplatform.student.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "students")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private LocalDateTime enrollmentDate;

    private String grade;

    @PrePersist
    protected void onCreate() {
        enrollmentDate = LocalDateTime.now();
    }

    // Helper method to get full name
    public String getFullName() {
        return firstName + " " + lastName;
    }
}
