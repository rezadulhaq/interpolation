package com.tryout.interpolation.service.impl;

// import com.example.tryout.dto.CategoryDTO;
// import com.example.tryout.entity.Category;
// import com.example.tryout.repository.CategoryRepository;
// import com.example.tryout.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tryout.interpolation.dto.AnswerOptionDTO;
import com.tryout.interpolation.dto.CategoryDTO;
import com.tryout.interpolation.dto.QuestionDTO;
import com.tryout.interpolation.dto.QuestionPackageDTO;
import com.tryout.interpolation.entity.Category;
import com.tryout.interpolation.repository.CategoryRepository;
import com.tryout.interpolation.service.CategoryService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<CategoryDTO> getAllCategories() {
        return categoryRepository.findAll().stream()
                .map(category -> new CategoryDTO(
                        category.getId(),
                        category.getName(),
                        category.getDescription(),
                        category.getDurationMinutes(),
                        category.getScoringFormula(),
                        category.getSlug(),
                        category.getQuestionPackages().stream()
                                .map(qp -> new QuestionPackageDTO(
                                        qp.getId(),
                                        qp.getName(),
                                        qp.getIsActive(),
                                        category.getId(), // Gunakan ID kategori
                                        qp.getQuestions().stream()
                                                .map(question -> new QuestionDTO(
                                                        question.getId(),
                                                        question.getText(),
                                                        question.getQuestionPackage().getId(),
                                                        question.getAnswers().stream()
                                                                .map(answer -> new AnswerOptionDTO(
                                                                        answer.getId(),
                                                                        answer.getText(),
                                                                        answer.getIsCorrect(),
                                                                        question.getId()))
                                                                .collect(Collectors.toList())))
                                                .collect(Collectors.toList())))
                                .collect(Collectors.toList())))
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDTO getCategoryById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found!"));
    
        return new CategoryDTO(
                category.getId(),
                category.getName(),
                category.getDescription(),
                category.getDurationMinutes(),
                category.getScoringFormula(),
                category.getSlug(), // Include the slug here
                category.getQuestionPackages().stream()
                        .map(qp -> {
                            // Map questions and answers
                            List<QuestionDTO> questions = qp.getQuestions().stream()
                                    .map(question -> {
                                        List<AnswerOptionDTO> answerOptions = question.getAnswers().stream()
                                                .map(answer -> new AnswerOptionDTO(
                                                        answer.getId(),
                                                        answer.getText(),
                                                        answer.getIsCorrect(),
                                                        question.getId()))
                                                .collect(Collectors.toList());
    
                                        return new QuestionDTO(
                                                question.getId(),
                                                question.getText(),
                                                qp.getId(),
                                                answerOptions);
                                    })
                                    .collect(Collectors.toList());
    
                            // Create QuestionPackageDTO
                            return new QuestionPackageDTO(
                                    qp.getId(),
                                    qp.getName(),
                                    qp.getIsActive(),
                                    category.getId(),
                                    questions);
                        })
                        .collect(Collectors.toList())
        );
    }
    

    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setName(categoryDTO.getName());
        category.setDescription(categoryDTO.getDescription());
        category.setDurationMinutes(categoryDTO.getDurationMinutes());
        category.setScoringFormula(categoryDTO.getScoringFormula());
        // Generate slug only if it is null or empty
        if (category.getSlug() == null || category.getSlug().isEmpty()) {
                category.setSlug(
                        categoryDTO.getName().trim().toLowerCase().replaceAll("\\s+", "-")
                );
        } else {
                // Retain the existing slug
                category.setSlug(category.getSlug());
        }

        Category savedCategory = categoryRepository.save(category);
        categoryDTO.setId(savedCategory.getId());
        return categoryDTO;
    }

    @Override
    public CategoryDTO updateCategory(Long id, CategoryDTO categoryDTO) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found!"));

        category.setName(categoryDTO.getName());
        category.setDescription(categoryDTO.getDescription());
        category.setDurationMinutes(categoryDTO.getDurationMinutes());
        category.setSlug(categoryDTO.getSlug());
        category.setScoringFormula(categoryDTO.getScoringFormula());

        categoryRepository.save(category);
        return categoryDTO;
    }

    @Override
    public void deleteCategory(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found!"));

        categoryRepository.delete(category);
    }
}
