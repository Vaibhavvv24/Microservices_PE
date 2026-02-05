package com.quizplatform.quiz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class QuizServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuizServiceApplication.class, args);
    }

    @org.springframework.context.annotation.Bean
    public org.springframework.boot.CommandLineRunner dataLoader(com.quizplatform.quiz.repository.QuizRepository quizRepository) {
        return args -> {
            if (quizRepository.count() == 0) {
                com.quizplatform.quiz.model.Quiz quiz = new com.quizplatform.quiz.model.Quiz();
                quiz.setTitle("General Knowledge");
                quiz.setCategory("General");
                quiz.setDifficulty("EASY");
                quiz.setDescription("A basic quiz");
                
                com.quizplatform.quiz.model.Question q1 = new com.quizplatform.quiz.model.Question();
                q1.setQuestionText("What is the capital of France?");
                q1.setPoints(10);
                q1.setCorrectAnswer("Paris");
                q1.setOptions(java.util.Arrays.asList("Paris", "London", "Berlin", "Madrid"));
                
                quiz.addQuestion(q1);
                
                quizRepository.save(quiz);
                System.out.println("Seeded Quiz ID: " + quiz.getId());
            }
        };
    }
}
