package com.quizplatform.student.service;

import com.quizplatform.student.dto.QuizAttemptDTO;

import java.util.List;

public interface QuizAttemptService {
    
    QuizAttemptDTO recordAttempt(QuizAttemptDTO attemptDTO);
    
    QuizAttemptDTO getAttemptById(Long id);
    
    List<QuizAttemptDTO> getAllAttempts();
    
    List<QuizAttemptDTO> getAttemptsByStudentId(Long studentId);
    
    List<QuizAttemptDTO> getAttemptsByQuizId(Long quizId);
    
    void deleteAttempt(Long id);
}
