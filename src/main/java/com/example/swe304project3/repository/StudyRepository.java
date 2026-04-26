package com.example.swe304project3.repository;

import com.example.swe304project3.entity.Study;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudyRepository extends JpaRepository<Study, Long> {
}