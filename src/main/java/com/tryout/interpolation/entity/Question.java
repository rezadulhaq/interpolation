package com.tryout.interpolation.entity;

import java.util.List;

import javax.persistence.*;

import com.tryout.interpolation.entity.QuestionPackage;

import lombok.Data;

@Data
@Entity
@Table(name = "questions")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;

    @ManyToOne
    @JoinColumn(name = "question_package_id")
    private QuestionPackage questionPackage;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<AnswerOption> answers;
}
