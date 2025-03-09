package dev.wisespirit.quiz_service.feign;

import dev.wisespirit.quiz_service.dto.QuestionDto;
import dev.wisespirit.quiz_service.model.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "QUESTION-SERVICE",path = "/question")
public interface QuizInterface {

    @GetMapping("/generate")
    ResponseEntity<List<Integer>> getQuestionsForQuiz(@RequestParam String categoryName,
                                                      @RequestParam Integer numQuestions);

    @PostMapping("/getQuestions")
    ResponseEntity<List<QuestionDto>> getQuestions(@RequestParam List<Integer> questionsIds);

    @PostMapping("/getScore")
    ResponseEntity<Integer> getScore(@RequestBody List<Response> responses);
}
