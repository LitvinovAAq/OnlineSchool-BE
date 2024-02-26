package ru.litvinov.onlineSchool.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "user_answers")
@Data
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class UserAnswers {

    @Id
    @Column(name = "user_answer_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "answer_date")
    private Timestamp answerDate;

    @ManyToOne
    @JoinColumn(name = "question_id", referencedColumnName = "question_id")
    private QuizQuestions question;

    @ManyToOne
    @JoinColumn(name = "chosen_choice_id", referencedColumnName = "choice_id")
    private AnswerChoices chosenChoice;

    @ManyToOne
    @JoinColumn(name = "app_user_id", referencedColumnName = "app_user_id")
    private AppUsers appUser;
}
