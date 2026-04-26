package com.example.swe304project3.repository;

import com.example.swe304project3.entity.Adviser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdviserRepository extends JpaRepository<Adviser, Long> {
}