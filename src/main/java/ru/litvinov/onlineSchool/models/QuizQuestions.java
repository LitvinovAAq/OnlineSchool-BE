package ru.litvinov.onlineSchool.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "quiz_questions")
@Data
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class QuizQuestions {

    @Id
    @Column(name = "question_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "question_title")
    private String questionTitle;

    @Column(name = "question_text")
    private String questionText;

    @ManyToOne
    @JoinColumn(name = "module_id", referencedColumnName = "module_id")
    private Modules module;

    @OneToMany(mappedBy = "question")
    private List<AnswerChoices> choices;

    @OneToMany(mappedBy = "question")
    private List<UserAnswers> userAnswers;
}
