package com.quizplatform.student.service;

import com.quizplatform.student.dto.QuizAttemptDTO;
import com.quizplatform.student.exception.QuizAttemptNotFoundException;
import com.quizplatform.student.model.QuizAttempt;
import com.quizplatform.student.repository.QuizAttemptRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class QuizAttemptServiceImpl implements QuizAttemptService {

    private final QuizAttemptRepository quizAttemptRepository;

    @Override
    @Transactional
    public QuizAttemptDTO recordAttempt(QuizAttemptDTO attemptDTO) {
        log.info("Recording quiz attempt for student: {} and quiz: {}", 
                attemptDTO.getStudentId(), attemptDTO.getQuizId());
        QuizAttempt attempt = convertToEntity(attemptDTO);
        QuizAttempt savedAttempt = quizAttemptRepository.save(attempt);
        log.info("Quiz attempt recorded with ID: {}", savedAttempt.getId());
        return convertToDTO(savedAttempt);
    }

    @Override
    @Transactional(readOnly = true)
    public QuizAttemptDTO getAttemptById(Long id) {
        log.info("Fetching quiz attempt with ID: {}", id);
        QuizAttempt attempt = quizAttemptRepository.findById(id)
                .orElseThrow(() -> new QuizAttemptNotFoundException("Quiz attempt not found with id: " + id));
        return convertToDTO(attempt);
    }

    @Override
    @Transactional(readOnly = true)
    public List<QuizAttemptDTO> getAllAttempts() {
        log.info("Fetching all quiz attempts");
        return quizAttemptRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<QuizAttemptDTO> getAttemptsByStudentId(Long studentId) {
        log.info("Fetching quiz attempts for student: {}", studentId);
        return quizAttemptRepository.findByStudentId(studentId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<QuizAttemptDTO> getAttemptsByQuizId(Long quizId) {
        log.info("Fetching quiz attempts for quiz: {}", quizId);
        return quizAttemptRepository.findByQuizId(quizId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deleteAttempt(Long id) {
        log.info("Deleting quiz attempt with ID: {}", id);
        if (!quizAttemptRepository.existsById(id)) {
            throw new QuizAttemptNotFoundException("Quiz attempt not found with id: " + id);
        }
        quizAttemptRepository.deleteById(id);
        log.info("Quiz attempt deleted with ID: {}", id);
    }

    // Conversion methods
    private QuizAttemptDTO convertToDTO(QuizAttempt attempt) {
        QuizAttemptDTO dto = new QuizAttemptDTO();
        dto.setId(attempt.getId());
        dto.setStudentId(attempt.getStudentId());
        dto.setQuizId(attempt.getQuizId());
        dto.setScore(attempt.getScore());
        dto.setAttemptDate(attempt.getAttemptDate());
        dto.setTimeTaken(attempt.getTimeTaken());
        return dto;
    }

    private QuizAttempt convertToEntity(QuizAttemptDTO dto) {
        QuizAttempt attempt = new QuizAttempt();
        attempt.setStudentId(dto.getStudentId());
        attempt.setQuizId(dto.getQuizId());
        attempt.setScore(dto.getScore());
        attempt.setTimeTaken(dto.getTimeTaken());
        return attempt;
    }
}
