package com.quizplatform.student.controller;

import com.quizplatform.student.dto.QuizAttemptDTO;
import com.quizplatform.student.service.QuizAttemptService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quiz-attempts")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "*")
public class QuizAttemptController {

    private final QuizAttemptService quizAttemptService;

    @PostMapping
    public ResponseEntity<QuizAttemptDTO> recordAttempt(@Valid @RequestBody QuizAttemptDTO attemptDTO) {
        log.info("POST request to record quiz attempt");
        QuizAttemptDTO createdAttempt = quizAttemptService.recordAttempt(attemptDTO);
        return new ResponseEntity<>(createdAttempt, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuizAttemptDTO> getAttemptById(@PathVariable Long id) {
        log.info("GET request for quiz attempt with ID: {}", id);
        QuizAttemptDTO attempt = quizAttemptService.getAttemptById(id);
        return ResponseEntity.ok(attempt);
    }

    @GetMapping
    public ResponseEntity<List<QuizAttemptDTO>> getAllAttempts() {
        log.info("GET request for all quiz attempts");
        List<QuizAttemptDTO> attempts = quizAttemptService.getAllAttempts();
        return ResponseEntity.ok(attempts);
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<QuizAttemptDTO>> getAttemptsByStudentId(@PathVariable Long studentId) {
        log.info("GET request for quiz attempts by student: {}", studentId);
        List<QuizAttemptDTO> attempts = quizAttemptService.getAttemptsByStudentId(studentId);
        return ResponseEntity.ok(attempts);
    }

    @GetMapping("/quiz/{quizId}")
    public ResponseEntity<List<QuizAttemptDTO>> getAttemptsByQuizId(@PathVariable Long quizId) {
        log.info("GET request for quiz attempts by quiz: {}", quizId);
        List<QuizAttemptDTO> attempts = quizAttemptService.getAttemptsByQuizId(quizId);
        return ResponseEntity.ok(attempts);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAttempt(@PathVariable Long id) {
        log.info("DELETE request for quiz attempt with ID: {}", id);
        quizAttemptService.deleteAttempt(id);
        return ResponseEntity.noContent().build();
    }
}
