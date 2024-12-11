package com.tryout.interpolation.service.impl;

import com.tryout.interpolation.dto.AnswerOptionDTO;
import com.tryout.interpolation.dto.QuestionDTO;
import com.tryout.interpolation.dto.QuestionPackageDTO;
import com.tryout.interpolation.entity.Category;
import com.tryout.interpolation.entity.QuestionPackage;
import com.tryout.interpolation.repository.CategoryRepository;
import com.tryout.interpolation.repository.QuestionPackageRepository;
import com.tryout.interpolation.service.QuestionPackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionPackageServiceImpl implements QuestionPackageService {

        @Autowired
        private QuestionPackageRepository questionPackageRepository;

        @Autowired
        private CategoryRepository categoryRepository;

        @Override
        public List<QuestionPackageDTO> getAllQuestionPackages() {
                return questionPackageRepository.findAll().stream()
                                .map(packageObj -> {
                                        // Mapping questions dan jawaban untuk setiap package
                                        List<QuestionDTO> questions = packageObj.getQuestions().stream()
                                                        .map(question -> {
                                                                // Mapping answer options untuk setiap question
                                                                List<AnswerOptionDTO> answerOptions = question
                                                                                .getAnswers().stream()
                                                                                .map(answer -> new AnswerOptionDTO(
                                                                                                answer.getId(),
                                                                                                answer.getText(),
                                                                                                answer.getIsCorrect(), // Correctness
                                                                                                                       // of
                                                                                                                       // the
                                                                                                                       // answer
                                                                                                question.getId())) // ID
                                                                                                                   // of
                                                                                                                   // the
                                                                                                                   // question
                                                                                                                   // for
                                                                                                                   // this
                                                                                                                   // answer
                                                                                                                   // option
                                                                                .collect(Collectors.toList());

                                                                // Return QuestionDTO dengan pertanyaan dan jawaban
                                                                return new QuestionDTO(
                                                                                question.getId(),
                                                                                question.getText(),
                                                                                packageObj.getId(), // ID dari question
                                                                                                    // package
                                                                                answerOptions // List jawaban untuk
                                                                                              // pertanyaan ini
                                                                );
                                                        })
                                                        .collect(Collectors.toList());

                                        // Return QuestionPackageDTO dengan daftar pertanyaan dan jawaban
                                        return new QuestionPackageDTO(
                                                        packageObj.getId(),
                                                        packageObj.getName(),
                                                        packageObj.getIsActive(),
                                                        packageObj.getCategory().getId(),
                                                        questions // Menyertakan pertanyaan dan jawaban untuk setiap
                                                                  // question package
                                        );
                                })
                                .collect(Collectors.toList());
        }

        @Override
        public QuestionPackageDTO getQuestionPackageById(Long id) {
                // Fetch the question package by id
                QuestionPackage packageObj = questionPackageRepository.findById(id)
                                .orElseThrow(() -> new RuntimeException("Question Package not found!"));

                // Mapping questions and their associated answer options
                List<QuestionDTO> questions = packageObj.getQuestions().stream()
                                .map(question -> {
                                        // Fetching answer options for each question
                                        List<AnswerOptionDTO> answerOptions = question.getAnswers().stream()
                                                        .map(answer -> new AnswerOptionDTO(
                                                                        answer.getId(),
                                                                        answer.getText(),
                                                                        answer.getIsCorrect(), // Indicates if answer is
                                                                                               // correct
                                                                        question.getId() // Question ID associated with
                                                                                         // this answer option
                                        ))
                                                        .collect(Collectors.toList());

                                        // Return each question with its answer options
                                        return new QuestionDTO(
                                                        question.getId(),
                                                        question.getText(),
                                                        packageObj.getId(), // ID of the question package
                                                        answerOptions // List of answer options for this question
                                        );
                                })
                                .collect(Collectors.toList());

                // Return the Question Package along with the questions and answer options
                return new QuestionPackageDTO(
                                packageObj.getId(),
                                packageObj.getName(),
                                packageObj.getIsActive(),
                                packageObj.getCategory().getId(),
                                questions // Include the questions and their answer options
                );
        }

        @Override
        public QuestionPackageDTO createQuestionPackage(QuestionPackageDTO dto) {
                Category category = categoryRepository.findById(dto.getCategoryId())
                                .orElseThrow(() -> new RuntimeException("Category not found!"));

                QuestionPackage packageObj = new QuestionPackage();
                packageObj.setName(dto.getName());
                packageObj.setCategory(category);

                QuestionPackage savedPackage = questionPackageRepository.save(packageObj);
                dto.setId(savedPackage.getId());
                return dto;
        }

        @Override
        public QuestionPackageDTO updateQuestionPackage(Long id, QuestionPackageDTO dto) {
                QuestionPackage packageObj = questionPackageRepository.findById(id)
                                .orElseThrow(() -> new RuntimeException("Question Package not found!"));

                Category category = categoryRepository.findById(dto.getCategoryId())
                                .orElseThrow(() -> new RuntimeException("Category not found!"));

                packageObj.setName(dto.getName());
                packageObj.setCategory(category);

                questionPackageRepository.save(packageObj);
                return dto;
        }

        @Override
        public void deleteQuestionPackage(Long id) {
                QuestionPackage packageObj = questionPackageRepository.findById(id)
                                .orElseThrow(() -> new RuntimeException("Question Package not found!"));

                questionPackageRepository.delete(packageObj);
        }
}
