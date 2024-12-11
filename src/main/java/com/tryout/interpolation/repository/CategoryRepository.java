package com.tryout.interpolation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tryout.interpolation.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
