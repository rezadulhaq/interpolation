package com.tryout.interpolation.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tryout.interpolation.dto.AnswerOptionDTO;
import com.tryout.interpolation.entity.AnswerOption;
import com.tryout.interpolation.entity.Question;
import com.tryout.interpolation.repository.AnswerOptionRepository;
import com.tryout.interpolation.repository.QuestionRepository;
import com.tryout.interpolation.service.AnswerOptionService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnswerOptionServiceImpl implements AnswerOptionService {

    @Autowired
    private AnswerOptionRepository answerOptionRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public List<AnswerOptionDTO> getAllAnswerOptions() {
        return answerOptionRepository.findAll().stream()
                .map(answerOption -> new AnswerOptionDTO(
                        answerOption.getId(),
                        answerOption.getText(),
                        answerOption.getIsCorrect(),
                        answerOption.getQuestion().getId()))
                .collect(Collectors.toList());
    }

    @Override
    public AnswerOptionDTO getAnswerOptionById(Long id) {
        AnswerOption answerOption = answerOptionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("AnswerOption not found!"));

        return new AnswerOptionDTO(
                answerOption.getId(),
                answerOption.getText(),
                answerOption.getIsCorrect(),
                answerOption.getQuestion().getId());
    }

    @Override
    public AnswerOptionDTO createAnswerOption(AnswerOptionDTO answerOptionDTO) {
        Question question = questionRepository.findById(answerOptionDTO.getQuestionId())
                .orElseThrow(() -> new RuntimeException("Question not found!"));

        AnswerOption answerOption = new AnswerOption();
        answerOption.setText(answerOptionDTO.getText());
        answerOption.setIsCorrect(answerOptionDTO.getIsCorrect());
        answerOption.setQuestion(question);

        AnswerOption savedAnswerOption = answerOptionRepository.save(answerOption);

        return new AnswerOptionDTO(
                savedAnswerOption.getId(),
                savedAnswerOption.getText(),
                savedAnswerOption.getIsCorrect(),
                savedAnswerOption.getQuestion().getId());
    }

    @Override
    public AnswerOptionDTO updateAnswerOption(Long id, AnswerOptionDTO answerOptionDTO) {
        AnswerOption answerOption = answerOptionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("AnswerOption not found!"));

        Question question = questionRepository.findById(answerOptionDTO.getQuestionId())
                .orElseThrow(() -> new RuntimeException("Question not found!"));

        answerOption.setText(answerOptionDTO.getText());
        answerOption.setIsCorrect(answerOptionDTO.getIsCorrect());
        answerOption.setQuestion(question);

        AnswerOption updatedAnswerOption = answerOptionRepository.save(answerOption);

        return new AnswerOptionDTO(
                updatedAnswerOption.getId(),
                updatedAnswerOption.getText(),
                updatedAnswerOption.getIsCorrect(),
                updatedAnswerOption.getQuestion().getId());
    }

    @Override
    public void deleteAnswerOption(Long id) {
        AnswerOption answerOption = answerOptionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("AnswerOption not found!"));
        answerOptionRepository.delete(answerOption);
    }
}
