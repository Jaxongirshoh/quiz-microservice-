package dev.wisespirit.question_service.service;

import dev.wisespirit.question_service.dto.QuestionDto;
import dev.wisespirit.question_service.model.Question;
import dev.wisespirit.question_service.model.Response;
import dev.wisespirit.question_service.repository.QuestionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class QuestionService {

    private final QuestionRepository questionRepository;

    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public List<Question> getAllQuestion() {
        System.out.println("getallquestion");
        List<Question> all = questionRepository.findAll();
        System.out.println("list of questions " + all);
        return all;

    }

    public Question save(Question question) {
        return questionRepository.save(question);
    }

    public List<Question> findByCategory(String category) {
        return questionRepository.findByCategory(category);
    }

    public ResponseEntity<List<Integer>> getQuestionsForQuiz(String categoryName, Integer numQuestions) {
        List<Integer> questions = questionRepository.findRandomQuestionsByCategory(numQuestions, categoryName);
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    public ResponseEntity<List<QuestionDto>> getQuestionsByIds(List<Integer> questionsIds) {
        List<QuestionDto> questionDtos = new ArrayList<>(questionsIds.size()+2);
        for (Integer id : questionsIds) {
            Optional<Question> question = questionRepository.findById(id);
            question.ifPresent(q->{
                QuestionDto questionDto = questionToDto(q);
                questionDtos.add(questionDto);
            });
        }
        return new ResponseEntity<>(questionDtos,HttpStatus.OK);
    }

    public QuestionDto questionToDto(Question question) {
        return new QuestionDto(question.getId(),
                question.getQuestionTitle(),
                question.getOption1(),
                question.getOption2(),
                question.getOption3(),
                question.getOption4());
    }

    public ResponseEntity<Integer> findScore(List<Response> responses) {
        int right=0;

        for (Response response : responses){
            Question question = questionRepository.findById(response.getId()).get();
            if (response.getResponse().equals(question.getRightAnswer())){
                right++;
            }
        }
        return ResponseEntity.ok(right);
    }
}
