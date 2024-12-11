package com.tryout.interpolation.entity;

import javax.persistence.*;

import com.tryout.interpolation.entity.Question;

import lombok.Data;

@Data
@Entity
@Table(name = "answer_options")
public class AnswerOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;
    private Boolean isCorrect;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;
}
