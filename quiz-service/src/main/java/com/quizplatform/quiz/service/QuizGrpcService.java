package com.quizplatform.quiz.service;

import com.quizplatform.common.*;
import com.quizplatform.quiz.model.Quiz;
import com.quizplatform.quiz.repository.QuizRepository;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@GrpcService
@RequiredArgsConstructor
public class QuizGrpcService extends QuizServiceGrpc.QuizServiceImplBase {

    private final QuizRepository quizRepository;

    @Override
    @Transactional(readOnly = true)
    public void getQuiz(QuizRequest request, StreamObserver<QuizResponse> responseObserver) {
        try {
            Long quizId = Long.parseLong(request.getQuizId());
            Quiz quiz = quizRepository.findById(quizId)
                    .orElseThrow(() -> new RuntimeException("Quiz not found with ID: " + quizId));

            List<com.quizplatform.common.Question> protoQuestions = quiz.getQuestions().stream()
                    .map(this::mapToProtoQuestion)
                    .collect(Collectors.toList());

            QuizResponse response = QuizResponse.newBuilder()
                    .setQuizId(String.valueOf(quiz.getId()))
                    .setTitle(quiz.getTitle())
                    .addAllQuestions(protoQuestions)
                    .build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (NumberFormatException e) {
            responseObserver.onError(io.grpc.Status.INVALID_ARGUMENT
                    .withDescription("Invalid Quiz ID format")
                    .asRuntimeException());
        } catch (Exception e) {
            responseObserver.onError(io.grpc.Status.INTERNAL
                    .withDescription("Error fetching quiz: " + e.getMessage())
                    .asRuntimeException());
        }
    }

    @Override
    public void submitResult(ResultRequest request, StreamObserver<ResultResponse> responseObserver) {
        // Logic to save result would go here. For now, we just acknowledge.
        // In a real app, we might save to a ResultRepository or publish an event.
        
        String message = String.format("Result received for Quiz %s, Student %s. Score: %d", 
                request.getQuizId(), request.getStudentId(), request.getScore());
        
        System.out.println(message); // Log to console for verifying communication

        ResultResponse response = ResultResponse.newBuilder()
                .setSuccess(true)
                .setMessage("Result processed successfully")
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    private com.quizplatform.common.Question mapToProtoQuestion(com.quizplatform.quiz.model.Question entity) {
        return com.quizplatform.common.Question.newBuilder()
                .setQuestionId(String.valueOf(entity.getId()))
                .setText(entity.getQuestionText())
                .addAllOptions(entity.getOptions()) // Assuming Question entity has getOptions() returning List<String>
                .build();
    }
}
