package com.example.spring_boot_quiz_app.repository;

import com.example.spring_boot_quiz_app.model.Result;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResultRepository extends JpaRepository<Result, Integer> {
}
