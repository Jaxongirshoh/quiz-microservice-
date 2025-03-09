package dev.wisespirit.quiz_service.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "quizes")
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    @ElementCollection
    private List<Integer> questionIds;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Integer> getQuestions() {
        return questionIds;
    }

    public void setQuestions(List<Integer> questions) {
        this.questionIds = questions;
    }

    @Override
    public String toString() {
        return "Quiz{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", questions=" + questionIds +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Quiz quiz = (Quiz) o;
        return Objects.equals(id, quiz.id) && Objects.equals(title, quiz.title) && Objects.equals(questionIds, quiz.questionIds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, questionIds);
    }
}
