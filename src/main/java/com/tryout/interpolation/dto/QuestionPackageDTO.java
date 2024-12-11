package com.tryout.interpolation.dto;

import java.util.List;

public class QuestionPackageDTO {
    private Long id;
    private String name;
    private String isActive;
    private Long categoryId;
    private List<QuestionDTO> questions;

    // Konstruktor dengan parameter lengkap
    public QuestionPackageDTO(Long id, String name, String isActive, Long categoryId, List<QuestionDTO> questions) {
        this.id = id;
        this.name = name;
        this.isActive = isActive;
        this.categoryId = categoryId;
        this.questions = questions;
    }

    // Konstruktor tanpa questions (untuk kasus ketika tidak ada questions)
    public QuestionPackageDTO(Long id, String name, String isActive, Long categoryId) {
        this.id = id;
        this.name = name;
        this.isActive = isActive;
        this.categoryId = categoryId;
        this.questions = null;  // Atur default null jika questions tidak diperlukan
    }

    // Getter dan Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIsActive() {
        return name;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public List<QuestionDTO> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionDTO> questions) {
        this.questions = questions;
    }
}
