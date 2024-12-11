package com.tryout.interpolation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.tryout.interpolation.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
