package com.tryout.interpolation.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "question_packages")
public class QuestionPackage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String isActive;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @OneToMany(mappedBy = "questionPackage", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Question> questions;

}
