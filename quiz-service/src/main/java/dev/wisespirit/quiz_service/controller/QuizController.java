package dev.wisespirit.quiz_service.controller;


import dev.wisespirit.quiz_service.dto.QuestionDto;
import dev.wisespirit.quiz_service.dto.QuizDto;
import dev.wisespirit.quiz_service.model.Quiz;
import dev.wisespirit.quiz_service.model.Response;
import dev.wisespirit.quiz_service.service.QuizService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizController {

    private final QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createQuiz(@RequestBody QuizDto dto){
        return quizService.createQuiz(dto.categoryName(), dto.numQuestions(), dto.title());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<List<QuestionDto>> getQuizQuestions(@PathVariable Integer id){
        return quizService.getQuizQuestions(id);
    }

    @PostMapping("/submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@RequestBody List<Response> response){
        return quizService.calculateResult(response);
    }
}
