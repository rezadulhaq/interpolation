package com.tryout.interpolation.dto;

import java.util.List;

public class QuestionDTO {
    private Long id;
    private String text;
    private Long questionPackageId;
    private List<AnswerOptionDTO> answerOptions;

    // Constructor
    public QuestionDTO(Long id, String text, Long questionPackageId, List<AnswerOptionDTO> answerOptions) {
        this.id = id;
        this.text = text;
        this.questionPackageId = questionPackageId;
        this.answerOptions = answerOptions;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getQuestionPackageId() {
        return questionPackageId;
    }

    public void setQuestionPackageId(Long questionPackageId) {
        this.questionPackageId = questionPackageId;
    }

    public List<AnswerOptionDTO> getAnswerOptions() {
        return answerOptions;
    }

    public void setAnswerOptions(List<AnswerOptionDTO> answerOptions) {
        this.answerOptions = answerOptions;
    }
}
