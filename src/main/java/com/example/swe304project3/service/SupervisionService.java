package com.example.swe304project3.service;

import com.example.swe304project3.entity.Adviser;
import com.example.swe304project3.entity.Study;
import com.example.swe304project3.entity.Supervision;
import com.example.swe304project3.repository.AdviserRepository;
import com.example.swe304project3.repository.StudyRepository;
import com.example.swe304project3.repository.SupervisionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SupervisionService {

    private final SupervisionRepository supervisionRepository;
    private final AdviserRepository adviserRepository;
    private final StudyRepository studyRepository;

    public List<Supervision> getAllSupervisions() {
        return supervisionRepository.findAll();
    }

    public Supervision getSupervisionById(Long id) {
        return supervisionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Supervision not found with id: " + id));
    }

    public Supervision createSupervision(Long adviserId, Long studyId, String student, String performance) {
        Adviser adviser = adviserRepository.findById(adviserId)
                .orElseThrow(() -> new RuntimeException("Adviser not found with id: " + adviserId));

        Study study = studyRepository.findById(studyId)
                .orElseThrow(() -> new RuntimeException("Study not found with id: " + studyId));

        Supervision supervision = Supervision.builder()
                .adviser(adviser)
                .study(study)
                .student(student)
                .performance(performance)
                .build();

        return supervisionRepository.save(supervision);
    }

    public Supervision updateSupervision(Long id, Long adviserId, Long studyId, String student, String performance) {
        Supervision existingSupervision = getSupervisionById(id);

        Adviser adviser = adviserRepository.findById(adviserId)
                .orElseThrow(() -> new RuntimeException("Adviser not found with id: " + adviserId));

        Study study = studyRepository.findById(studyId)
                .orElseThrow(() -> new RuntimeException("Study not found with id: " + studyId));

        existingSupervision.setAdviser(adviser);
        existingSupervision.setStudy(study);
        existingSupervision.setStudent(student);
        existingSupervision.setPerformance(performance);

        return supervisionRepository.save(existingSupervision);
    }

    public void deleteSupervision(Long id) {
        Supervision existingSupervision = getSupervisionById(id);
        supervisionRepository.delete(existingSupervision);
    }
}