package com.quizplatform.student.controller;

import com.quizplatform.common.QuizRequest;
import com.quizplatform.common.QuizResponse;
import com.quizplatform.common.QuizServiceGrpc;
import com.quizplatform.common.ResultRequest;
import com.quizplatform.common.ResultResponse;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student/quiz")
public class StudentQuizController {

    @GrpcClient("quiz-service")
    private QuizServiceGrpc.QuizServiceBlockingStub quizServiceStub;

    @GetMapping("/{quizId}")
    public String startQuiz(@PathVariable String quizId) {
        try {
            QuizRequest request = QuizRequest.newBuilder()
                    .setQuizId(quizId)
                    .build();

            QuizResponse response = quizServiceStub.getQuiz(request);
            
            // Format response for display
            StringBuilder sb = new StringBuilder();
            sb.append("Quiz: ").append(response.getTitle()).append("\n");
            response.getQuestionsList().forEach(q -> {
                sb.append("Q: ").append(q.getText()).append("\n");
                q.getOptionsList().forEach(o -> sb.append(" - ").append(o).append("\n"));
                sb.append("\n");
            });
            
            return sb.toString();
        } catch (Exception e) {
            return "Error starting quiz: " + e.getMessage();
        }
    }

    @PostMapping("/{quizId}/submit")
    public String submitQuiz(@PathVariable String quizId, @RequestParam String studentId, @RequestParam int score) {
        try {
            ResultRequest request = ResultRequest.newBuilder()
                    .setQuizId(quizId)
                    .setStudentId(studentId)
                    .setScore(score)
                    .build();

            ResultResponse response = quizServiceStub.submitResult(request);
            
            return response.getMessage();
        } catch (Exception e) {
            return "Error submitting quiz: " + e.getMessage();
        }
    }
}
