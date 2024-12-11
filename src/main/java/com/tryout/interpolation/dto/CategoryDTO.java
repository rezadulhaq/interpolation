package com.tryout.interpolation.dto;

import java.util.List;

public class CategoryDTO {
    private Long id;
    private String name;
    private String description;
    private Integer durationMinutes;
    private String scoringFormula;
    private String slug;
    private List<QuestionPackageDTO> questionPackages; // Tambahkan properti ini

    // Konstruktor dengan parameter
    public CategoryDTO(Long id, String name, String description, Integer durationMinutes, String scoringFormula,
            String slug,
            List<QuestionPackageDTO> questionPackages) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.durationMinutes = durationMinutes;
        this.scoringFormula = scoringFormula;
        this.slug = slug;
        this.questionPackages = questionPackages;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getDurationMinutes() {
        return durationMinutes;
    }

    public void setDurationMinutes(Integer durationMinutes) {
        this.durationMinutes = durationMinutes;
    }

    public String getScoringFormula() {
        return scoringFormula;
    }

    public void setScoringFormula(String scoringFormula) {
        this.scoringFormula = scoringFormula;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public List<QuestionPackageDTO> getQuestionPackages() {
        return questionPackages;
    }

    public void setQuestionPackages(List<QuestionPackageDTO> questionPackages) {
        this.questionPackages = questionPackages;
    }
}
