package com.tryout.interpolation.service;

import com.tryout.interpolation.dto.AnswerOptionDTO;
import java.util.List;

public interface AnswerOptionService {
    List<AnswerOptionDTO> getAllAnswerOptions();

    AnswerOptionDTO getAnswerOptionById(Long id);

    AnswerOptionDTO createAnswerOption(AnswerOptionDTO answerOptionDTO);

    AnswerOptionDTO updateAnswerOption(Long id, AnswerOptionDTO answerOptionDTO);

    void deleteAnswerOption(Long id);
}
