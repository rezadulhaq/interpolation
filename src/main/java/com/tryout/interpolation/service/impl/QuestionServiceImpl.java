package com.tryout.interpolation.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tryout.interpolation.dto.AnswerOptionDTO;
import com.tryout.interpolation.dto.QuestionDTO;
import com.tryout.interpolation.entity.AnswerOption;
import com.tryout.interpolation.entity.Question;
import com.tryout.interpolation.entity.QuestionPackage;
import com.tryout.interpolation.repository.AnswerOptionRepository;
import com.tryout.interpolation.repository.QuestionRepository;
import com.tryout.interpolation.repository.QuestionPackageRepository;
import com.tryout.interpolation.service.QuestionService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private QuestionPackageRepository questionPackageRepository;

    @Autowired
    private AnswerOptionRepository answerOptionRepository;  // Repository untuk answer options

    @Override
    public List<QuestionDTO> getAllQuestions() {
        return questionRepository.findAll().stream()
                .map(question -> {
                    // Map jawaban untuk setiap pertanyaan
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
                            question.getQuestionPackage().getId(),
                            answerOptions // Sertakan jawaban
                    );
                })
                .collect(Collectors.toList());
    }

    @Override
    public QuestionDTO getQuestionById(Long id) {
        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Question not found!"));

        // Map jawaban untuk pertanyaan berdasarkan ID
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
                question.getQuestionPackage().getId(),
                answerOptions // Sertakan jawaban
        );
    }

    @Override
    public QuestionDTO createQuestion(QuestionDTO questionDTO) {
        QuestionPackage questionPackage = questionPackageRepository.findById(questionDTO.getQuestionPackageId())
                .orElseThrow(() -> new RuntimeException("Question Package not found!"));

        Question question = new Question();
        question.setText(questionDTO.getText());
        question.setQuestionPackage(questionPackage);

        Question savedQuestion = questionRepository.save(question);

        // Menyimpan jawaban jika ada
        if (questionDTO.getAnswerOptions() != null) {
            questionDTO.getAnswerOptions().forEach(answerDTO -> {
                AnswerOption answerOption = new AnswerOption();
                answerOption.setText(answerDTO.getText());
                answerOption.setIsCorrect(answerDTO.getIsCorrect());
                answerOption.setQuestion(savedQuestion);
                answerOptionRepository.save(answerOption);
            });
        }

        return new QuestionDTO(
                savedQuestion.getId(),
                savedQuestion.getText(),
                savedQuestion.getQuestionPackage().getId(),
                questionDTO.getAnswerOptions() // Sertakan jawaban
        );
    }

    @Override
    public QuestionDTO updateQuestion(Long id, QuestionDTO questionDTO) {
        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Question not found!"));

        QuestionPackage questionPackage = questionPackageRepository.findById(questionDTO.getQuestionPackageId())
                .orElseThrow(() -> new RuntimeException("Question Package not found!"));

        question.setText(questionDTO.getText());
        question.setQuestionPackage(questionPackage);

        // Update question
        Question updatedQuestion = questionRepository.save(question);

        // Update jawaban jika ada
        if (questionDTO.getAnswerOptions() != null) {
            questionDTO.getAnswerOptions().forEach(answerDTO -> {
                AnswerOption answerOption = new AnswerOption();
                answerOption.setText(answerDTO.getText());
                answerOption.setIsCorrect(answerDTO.getIsCorrect());
                answerOption.setQuestion(updatedQuestion);
                answerOptionRepository.save(answerOption);
            });
        }

        return new QuestionDTO(
                updatedQuestion.getId(),
                updatedQuestion.getText(),
                updatedQuestion.getQuestionPackage().getId(),
                questionDTO.getAnswerOptions() // Sertakan jawaban
        );
    }

    @Override
    public void deleteQuestion(Long id) {
        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Question not found!"));
        questionRepository.delete(question);
    }
}
