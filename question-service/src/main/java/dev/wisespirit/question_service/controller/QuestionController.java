package dev.wisespirit.question_service.controller;


import dev.wisespirit.question_service.dto.QuestionDto;
import dev.wisespirit.question_service.model.Question;
import dev.wisespirit.question_service.model.Response;
import dev.wisespirit.question_service.service.QuestionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/question")
public class QuestionController {

    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("/allQuestions")
    public ResponseEntity<List<Question>> getAllQuestions(){
        List<Question> allQuestion = questionService.getAllQuestion();
        return new ResponseEntity<>(allQuestion, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Question> save(@RequestBody Question question){
        return new ResponseEntity<>(questionService.save(question),HttpStatus.CREATED);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Question>> getByCategory(@PathVariable String category){
        return new ResponseEntity<>(questionService.findByCategory(category),HttpStatus.OK);
    }

    @GetMapping("/generate")
    public ResponseEntity<List<Integer>> getQuestionsForQuiz(@RequestParam String categoryName,
                                                             @RequestParam Integer numQuestions ){
        return questionService.getQuestionsForQuiz(categoryName,numQuestions);
    }

    @PostMapping("/getQuestions")
    public ResponseEntity<List<QuestionDto>> getQuestions(@RequestParam List<Integer> questionsIds){
        return questionService.getQuestionsByIds(questionsIds);
    }

    @PostMapping("/getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses){
        System.out.println("response{}"+responses);
        return questionService.findScore(responses);
    }

}
