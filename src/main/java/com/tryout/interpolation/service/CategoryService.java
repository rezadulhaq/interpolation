package com.tryout.interpolation.service;


// import com.example.tryout.dto.CategoryDTO;
import java.util.List;

import org.springframework.stereotype.Service;

import com.tryout.interpolation.dto.CategoryDTO;

@Service
public interface CategoryService {
    List<CategoryDTO> getAllCategories();
    CategoryDTO getCategoryById(Long id);
    CategoryDTO createCategory(CategoryDTO categoryDTO);
    CategoryDTO updateCategory(Long id, CategoryDTO categoryDTO);
    void deleteCategory(Long id);
}
