package com.tryout.interpolation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.tryout.interpolation.dto.QuestionPackageDTO;
import com.tryout.interpolation.service.QuestionPackageService;

import java.util.List;

@RestController
@RequestMapping("/api/admin/question-packages")
public class QuestionPackageController {

    @Autowired
    private QuestionPackageService questionPackageService;

    @GetMapping
    public ResponseEntity<?> getAllQuestionPackages() {
        try {
            List<QuestionPackageDTO> packages = questionPackageService.getAllQuestionPackages();
            return ResponseEntity.ok(packages);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to retrieve question packages: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getQuestionPackageById(@PathVariable Long id) {
        try {
            QuestionPackageDTO questionPackage = questionPackageService.getQuestionPackageById(id);
            return ResponseEntity.ok(questionPackage);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Question package not found: " + e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> createQuestionPackage(@RequestBody QuestionPackageDTO questionPackageDTO) {
        try {
            QuestionPackageDTO createdPackage = questionPackageService.createQuestionPackage(questionPackageDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdPackage);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Failed to create question package: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateQuestionPackage(@PathVariable Long id, @RequestBody QuestionPackageDTO questionPackageDTO) {
        try {
            QuestionPackageDTO updatedPackage = questionPackageService.updateQuestionPackage(id, questionPackageDTO);
            return ResponseEntity.ok(updatedPackage);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Failed to update question package: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteQuestionPackage(@PathVariable Long id) {
        try {
            questionPackageService.deleteQuestionPackage(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Failed to delete question package: " + e.getMessage());
        }
    }
}
