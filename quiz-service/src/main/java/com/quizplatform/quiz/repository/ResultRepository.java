package com.quizplatform.quiz.repository;

import com.quizplatform.quiz.model.Result;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ResultRepository extends JpaRepository<Result, Long> {
    List<Result> findByQuizId(String quizId);
    List<Result> findByStudentId(String studentId);
}
