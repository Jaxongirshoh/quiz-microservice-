package dev.wisespirit.quiz_service.service;


import dev.wisespirit.quiz_service.dto.QuestionDto;
import dev.wisespirit.quiz_service.feign.QuizInterface;
import dev.wisespirit.quiz_service.model.Quiz;
import dev.wisespirit.quiz_service.model.Response;
import dev.wisespirit.quiz_service.repository.QuizRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class QuizService {
    private final QuizRepository quizRepository;
    private final QuizInterface quizInterface;

    public QuizService(QuizRepository quizRepository, QuizInterface quizInterface) {
        this.quizRepository = quizRepository;
        this.quizInterface = quizInterface;
    }

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
        List<Integer> questionIds = quizInterface.getQuestionsForQuiz(category, numQ).getBody();
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questionIds);
        quizRepository.save(quiz);
        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionDto>> getQuizQuestions(Integer id) {
        Quiz quiz = quizRepository.getQuizById(id);
        List<Integer> questionIds = quiz.getQuestions();
        List<QuestionDto> questionDtos = quizInterface.getQuestions(questionIds).getBody();
        return new ResponseEntity<>(questionDtos, HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculateResult(List<Response> responses) {
        Integer rightAnswers = quizInterface.getScore(responses).getBody();
        return new ResponseEntity<>(rightAnswers, HttpStatus.OK);
    }
}
