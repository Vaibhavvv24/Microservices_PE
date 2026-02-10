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
    private final com.quizplatform.quiz.repository.ResultRepository resultRepository;

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
    @Transactional
    public void submitResult(ResultRequest request, StreamObserver<ResultResponse> responseObserver) {
        try {
            com.quizplatform.quiz.model.Result result = new com.quizplatform.quiz.model.Result();
            result.setQuizId(request.getQuizId());
            result.setStudentId(request.getStudentId());
            result.setScore(request.getScore());
            
            resultRepository.save(result);
            
            String message = String.format("Result stored for Quiz %s, Student %s. Score: %d", 
                    request.getQuizId(), request.getStudentId(), request.getScore());
            System.out.println(message);
    
            ResultResponse response = ResultResponse.newBuilder()
                    .setSuccess(true)
                    .setMessage("Result processed successfully")
                    .build();
    
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (Exception e) {
             responseObserver.onError(io.grpc.Status.INTERNAL
                    .withDescription("Error saving result: " + e.getMessage())
                    .asRuntimeException());
        }
    }

    @Override
    public void getAllResults(GetAllResultsRequest request, StreamObserver<GetAllResultsResponse> responseObserver) {
        try {
            String quizId = request.getQuizId();
            List<com.quizplatform.quiz.model.Result> results = resultRepository.findByQuizId(quizId);
            
            List<ResultEntry> resultEntries = results.stream()
                .map(r -> ResultEntry.newBuilder()
                    .setStudentId(r.getStudentId())
                    .setScore(r.getScore())
                    .setTimestamp(r.getTimestamp() != null ? r.getTimestamp().toString() : "")
                    .build())
                .collect(Collectors.toList());
                
            GetAllResultsResponse response = GetAllResultsResponse.newBuilder()
                .addAllResults(resultEntries)
                .build();
                
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(io.grpc.Status.INTERNAL
                    .withDescription("Error fetching results: " + e.getMessage())
                    .asRuntimeException());
        }
    }

    private com.quizplatform.common.Question mapToProtoQuestion(com.quizplatform.quiz.model.Question entity) {
        return com.quizplatform.common.Question.newBuilder()
                .setQuestionId(String.valueOf(entity.getId()))
                .setText(entity.getQuestionText())
                .addAllOptions(entity.getOptions()) // Assuming Question entity has getOptions() returning List<String>
                .build();
    }
}
