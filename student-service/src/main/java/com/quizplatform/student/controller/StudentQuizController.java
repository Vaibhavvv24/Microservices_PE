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
@lombok.RequiredArgsConstructor
public class StudentQuizController {

    private final com.quizplatform.student.service.QuizClientService quizClientService;

    @GetMapping("/{quizId}")
    public String startQuiz(@PathVariable String quizId) {
        return quizClientService.getQuiz(quizId);
    }

    @PostMapping("/{quizId}/submit")
    public String submitQuiz(@PathVariable String quizId, @RequestParam String studentId, @RequestParam int score) {
        return quizClientService.submitQuizResult(quizId, studentId, score);
    }

    @GetMapping("/{quizId}/scores")
    public java.util.List<java.util.Map<String, Object>> getStudentScores(@PathVariable String quizId) {
        return quizClientService.getQuizResults(quizId);
    }
}
