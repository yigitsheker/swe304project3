package com.example.swe304project3.controller;

import com.example.swe304project3.entity.Study;
import com.example.swe304project3.service.StudyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/studies")
@RequiredArgsConstructor
public class StudyController {

    private final StudyService studyService;

    @GetMapping
    public List<Study> getAllStudies() {
        return studyService.getAllStudies();
    }

    @GetMapping("/{id}")
    public Study getStudyById(@PathVariable Long id) {
        return studyService.getStudyById(id);
    }

    @PostMapping
    public Study createStudy(@Valid @RequestBody Study study) {
        return studyService.createStudy(study);
    }

    @PutMapping("/{id}")
    public Study updateStudy(@PathVariable Long id, @Valid @RequestBody Study study) {
        return studyService.updateStudy(id, study);
    }

    @DeleteMapping("/{id}")
    public String deleteStudy(@PathVariable Long id) {
        studyService.deleteStudy(id);
        return "Study deleted successfully with id: " + id;
    }
}