package com.example.swe304project3.repository;

import com.example.swe304project3.entity.Supervision;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupervisionRepository extends JpaRepository<Supervision, Long> {
}