package com.quizplatform.quiz.service;

import com.quizplatform.quiz.dto.QuizDTO;

import java.util.List;

public interface QuizService {
    
    QuizDTO createQuiz(QuizDTO quizDTO);
    
    QuizDTO getQuizById(Long id);
    
    List<QuizDTO> getAllQuizzes();
    
    List<QuizDTO> getQuizzesByCategory(String category);
    
    List<QuizDTO> getQuizzesByDifficulty(String difficulty);
    
    QuizDTO updateQuiz(Long id, QuizDTO quizDTO);
    
    void deleteQuiz(Long id);
}
