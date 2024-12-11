package com.tryout.interpolation.service;

import com.tryout.interpolation.dto.QuestionPackageDTO;

import java.util.List;

public interface QuestionPackageService {
    List<QuestionPackageDTO> getAllQuestionPackages();
    QuestionPackageDTO getQuestionPackageById(Long id);
    QuestionPackageDTO createQuestionPackage(QuestionPackageDTO dto);
    QuestionPackageDTO updateQuestionPackage(Long id, QuestionPackageDTO dto);
    void deleteQuestionPackage(Long id);
}
