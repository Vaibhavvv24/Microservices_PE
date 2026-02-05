package com.quizplatform.student.exception;

public class QuizAttemptNotFoundException extends RuntimeException {
    
    public QuizAttemptNotFoundException(String message) {
        super(message);
    }
    
    public QuizAttemptNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
