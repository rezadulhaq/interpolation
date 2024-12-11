package com.tryout.interpolation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.tryout.interpolation.entity.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
}
