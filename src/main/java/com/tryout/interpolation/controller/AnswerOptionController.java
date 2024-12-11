package com.tryout.interpolation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.tryout.interpolation.dto.AnswerOptionDTO;
import com.tryout.interpolation.service.AnswerOptionService;

import java.util.List;

@RestController
@RequestMapping("/api/admin/answer-options")
public class AnswerOptionController {

    @Autowired
    private AnswerOptionService answerOptionService;

    @GetMapping
    public ResponseEntity<?> getAllAnswerOptions() {
        try {
            List<AnswerOptionDTO> answerOptions = answerOptionService.getAllAnswerOptions();
            return ResponseEntity.ok(answerOptions);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to retrieve answer options: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAnswerOptionById(@PathVariable Long id) {
        try {
            AnswerOptionDTO answerOption = answerOptionService.getAnswerOptionById(id);
            return ResponseEntity.ok(answerOption);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Answer option not found: " + e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> createAnswerOption(@RequestBody AnswerOptionDTO answerOptionDTO) {
        try {
            AnswerOptionDTO createdAnswerOption = answerOptionService.createAnswerOption(answerOptionDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdAnswerOption);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Failed to create answer option: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAnswerOption(@PathVariable Long id, @RequestBody AnswerOptionDTO answerOptionDTO) {
        try {
            AnswerOptionDTO updatedAnswerOption = answerOptionService.updateAnswerOption(id, answerOptionDTO);
            return ResponseEntity.ok(updatedAnswerOption);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Failed to update answer option: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAnswerOption(@PathVariable Long id) {
        try {
            answerOptionService.deleteAnswerOption(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Failed to delete answer option: " + e.getMessage());
        }
    }
}
