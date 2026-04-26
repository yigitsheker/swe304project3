package com.example.swe304project3.service;

import com.example.swe304project3.entity.Study;
import com.example.swe304project3.repository.StudyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudyService {

    private final StudyRepository studyRepository;

    public List<Study> getAllStudies() {
        return studyRepository.findAll();
    }

    public Study getStudyById(Long id) {
        return studyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Study not found with id: " + id));
    }

    public Study createStudy(Study study) {
        return studyRepository.save(study);
    }

    public Study updateStudy(Long id, Study updatedStudy) {
        Study existingStudy = getStudyById(id);

        existingStudy.setTitle(updatedStudy.getTitle());
        existingStudy.setDescription(updatedStudy.getDescription());

        return studyRepository.save(existingStudy);
    }

    public void deleteStudy(Long id) {
        Study existingStudy = getStudyById(id);
        studyRepository.delete(existingStudy);
    }
}