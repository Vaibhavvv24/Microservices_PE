package com.quizplatform.student.service;

import com.quizplatform.common.*;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class QuizClientService {

    @GrpcClient("quiz-service")
    private QuizServiceGrpc.QuizServiceBlockingStub quizServiceStub;

    public String getQuiz(String quizId) {
        try {
            QuizRequest request = QuizRequest.newBuilder().setQuizId(quizId).build();
            QuizResponse response = quizServiceStub.getQuiz(request);
            
            StringBuilder sb = new StringBuilder();
            sb.append("Quiz: ").append(response.getTitle()).append("\n");
            for (Question q : response.getQuestionsList()) {
                sb.append("Q: ").append(q.getText()).append("\n");
                for (String opt : q.getOptionsList()) {
                    sb.append(" - ").append(opt).append("\n");
                }
            }
            return sb.toString();
        } catch (Exception e) {
            return "Error fetching quiz: " + e.getMessage();
        }
    }

    public String submitQuizResult(String quizId, String studentId, int score) {
        try {
            ResultRequest request = ResultRequest.newBuilder()
                    .setQuizId(quizId)
                    .setStudentId(studentId)
                    .setScore(score)
                    .build();
            
            ResultResponse response = quizServiceStub.submitResult(request);
            return response.getMessage();
        } catch (Exception e) {
            return "Error submitting result: " + e.getMessage();
        }
    }

    public List<Map<String, Object>> getQuizResults(String quizId) {
        try {
            GetAllResultsRequest request = GetAllResultsRequest.newBuilder()
                .setQuizId(quizId)
                .build();
                
            GetAllResultsResponse response = quizServiceStub.getAllResults(request);
            
            return response.getResultsList().stream().map(r -> {
                Map<String, Object> map = new HashMap<>();
                map.put("studentId", r.getStudentId());
                map.put("score", r.getScore());
                map.put("timestamp", r.getTimestamp());
                return map;
            }).collect(Collectors.toList());
        } catch (Exception e) {
            List<Map<String, Object>> errorList = new ArrayList<>();
            Map<String, Object> errorMap = new HashMap<>();
            errorMap.put("error", "Error fetching results: " + e.getMessage());
            errorList.add(errorMap);
            return errorList;
        }
    }
}
