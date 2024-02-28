package ru.litvinov.onlineSchool.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "answer_choices")
@Data
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class AnswerChoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "choice_id", nullable = false, unique = true)
    private Integer id;

    @Column(name = "choice_text")
    private String choiceText;

    @Column(name = "is_correct")
    private boolean isCorrect;

    @ManyToOne
    @JoinColumn(name = "question_id", referencedColumnName = "question_id")
    private QuizQuestion question;

    @OneToMany(mappedBy = "chosenChoice")
    private List<UserAnswer> answers;
}
