package com.quizplatform.quiz.service;

import com.quizplatform.quiz.dto.QuestionDTO;
import com.quizplatform.quiz.dto.QuizDTO;
import com.quizplatform.quiz.exception.QuizNotFoundException;
import com.quizplatform.quiz.model.Question;
import com.quizplatform.quiz.model.Quiz;
import com.quizplatform.quiz.repository.QuizRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class QuizServiceImpl implements QuizService {

    private final QuizRepository quizRepository;

    @Override
    @Transactional
    public QuizDTO createQuiz(QuizDTO quizDTO) {
        log.info("Creating new quiz: {}", quizDTO.getTitle());
        Quiz quiz = convertToEntity(quizDTO);
        Quiz savedQuiz = quizRepository.save(quiz);
        log.info("Quiz created with ID: {}", savedQuiz.getId());
        return convertToDTO(savedQuiz);
    }

    @Override
    @Transactional(readOnly = true)
    public QuizDTO getQuizById(Long id) {
        log.info("Fetching quiz with ID: {}", id);
        Quiz quiz = quizRepository.findById(id)
                .orElseThrow(() -> new QuizNotFoundException("Quiz not found with id: " + id));
        return convertToDTO(quiz);
    }

    @Override
    @Transactional(readOnly = true)
    public List<QuizDTO> getAllQuizzes() {
        log.info("Fetching all quizzes");
        return quizRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<QuizDTO> getQuizzesByCategory(String category) {
        log.info("Fetching quizzes by category: {}", category);
        return quizRepository.findByCategory(category).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<QuizDTO> getQuizzesByDifficulty(String difficulty) {
        log.info("Fetching quizzes by difficulty: {}", difficulty);
        return quizRepository.findByDifficulty(difficulty).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public QuizDTO updateQuiz(Long id, QuizDTO quizDTO) {
        log.info("Updating quiz with ID: {}", id);
        Quiz existingQuiz = quizRepository.findById(id)
                .orElseThrow(() -> new QuizNotFoundException("Quiz not found with id: " + id));

        existingQuiz.setTitle(quizDTO.getTitle());
        existingQuiz.setDescription(quizDTO.getDescription());
        existingQuiz.setCategory(quizDTO.getCategory());
        existingQuiz.setDifficulty(quizDTO.getDifficulty());

        // Clear existing questions and add new ones
        existingQuiz.getQuestions().clear();
        quizDTO.getQuestions().forEach(questionDTO -> {
            Question question = convertQuestionDTOToEntity(questionDTO);
            existingQuiz.addQuestion(question);
        });

        Quiz updatedQuiz = quizRepository.save(existingQuiz);
        log.info("Quiz updated with ID: {}", updatedQuiz.getId());
        return convertToDTO(updatedQuiz);
    }

    @Override
    @Transactional
    public void deleteQuiz(Long id) {
        log.info("Deleting quiz with ID: {}", id);
        if (!quizRepository.existsById(id)) {
            throw new QuizNotFoundException("Quiz not found with id: " + id);
        }
        quizRepository.deleteById(id);
        log.info("Quiz deleted with ID: {}", id);
    }

    // Conversion methods
    private QuizDTO convertToDTO(Quiz quiz) {
        QuizDTO dto = new QuizDTO();
        dto.setId(quiz.getId());
        dto.setTitle(quiz.getTitle());
        dto.setDescription(quiz.getDescription());
        dto.setCategory(quiz.getCategory());
        dto.setDifficulty(quiz.getDifficulty());
        dto.setCreatedDate(quiz.getCreatedDate());
        dto.setQuestions(quiz.getQuestions().stream()
                .map(this::convertQuestionToDTO)
                .collect(Collectors.toList()));
        return dto;
    }

    private Quiz convertToEntity(QuizDTO dto) {
        Quiz quiz = new Quiz();
        quiz.setTitle(dto.getTitle());
        quiz.setDescription(dto.getDescription());
        quiz.setCategory(dto.getCategory());
        quiz.setDifficulty(dto.getDifficulty());
        
        dto.getQuestions().forEach(questionDTO -> {
            Question question = convertQuestionDTOToEntity(questionDTO);
            quiz.addQuestion(question);
        });
        
        return quiz;
    }

    private QuestionDTO convertQuestionToDTO(Question question) {
        QuestionDTO dto = new QuestionDTO();
        dto.setId(question.getId());
        dto.setQuestionText(question.getQuestionText());
        dto.setOptions(question.getOptions());
        dto.setCorrectAnswer(question.getCorrectAnswer());
        dto.setPoints(question.getPoints());
        return dto;
    }

    private Question convertQuestionDTOToEntity(QuestionDTO dto) {
        Question question = new Question();
        question.setQuestionText(dto.getQuestionText());
        question.setOptions(dto.getOptions());
        question.setCorrectAnswer(dto.getCorrectAnswer());
        question.setPoints(dto.getPoints());
        return question;
    }
}
