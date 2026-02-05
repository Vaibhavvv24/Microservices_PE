package com.quizplatform.quiz.controller;

import com.quizplatform.quiz.dto.QuizDTO;
import com.quizplatform.quiz.service.QuizService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quizzes")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "*")
public class QuizController {

    private final QuizService quizService;

    @PostMapping
    public ResponseEntity<QuizDTO> createQuiz(@Valid @RequestBody QuizDTO quizDTO) {
        log.info("POST request to create quiz: {}", quizDTO.getTitle());
        QuizDTO createdQuiz = quizService.createQuiz(quizDTO);
        return new ResponseEntity<>(createdQuiz, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuizDTO> getQuizById(@PathVariable Long id) {
        log.info("GET request for quiz with ID: {}", id);
        QuizDTO quiz = quizService.getQuizById(id);
        return ResponseEntity.ok(quiz);
    }

    @GetMapping
    public ResponseEntity<List<QuizDTO>> getAllQuizzes() {
        log.info("GET request for all quizzes");
        List<QuizDTO> quizzes = quizService.getAllQuizzes();
        return ResponseEntity.ok(quizzes);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<QuizDTO>> getQuizzesByCategory(@PathVariable String category) {
        log.info("GET request for quizzes by category: {}", category);
        List<QuizDTO> quizzes = quizService.getQuizzesByCategory(category);
        return ResponseEntity.ok(quizzes);
    }

    @GetMapping("/difficulty/{difficulty}")
    public ResponseEntity<List<QuizDTO>> getQuizzesByDifficulty(@PathVariable String difficulty) {
        log.info("GET request for quizzes by difficulty: {}", difficulty);
        List<QuizDTO> quizzes = quizService.getQuizzesByDifficulty(difficulty);
        return ResponseEntity.ok(quizzes);
    }

    @PutMapping("/{id}")
    public ResponseEntity<QuizDTO> updateQuiz(@PathVariable Long id, @Valid @RequestBody QuizDTO quizDTO) {
        log.info("PUT request to update quiz with ID: {}", id);
        QuizDTO updatedQuiz = quizService.updateQuiz(id, quizDTO);
        return ResponseEntity.ok(updatedQuiz);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuiz(@PathVariable Long id) {
        log.info("DELETE request for quiz with ID: {}", id);
        quizService.deleteQuiz(id);
        return ResponseEntity.noContent().build();
    }
}
