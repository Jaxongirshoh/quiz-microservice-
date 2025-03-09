package dev.wisespirit.quiz_service.repository;

import dev.wisespirit.quiz_service.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRepository extends JpaRepository<Quiz, Integer> {
    Quiz getQuizById(Integer id);
}