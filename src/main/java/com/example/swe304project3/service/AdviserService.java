package com.example.swe304project3.service;

import com.example.swe304project3.entity.Adviser;
import com.example.swe304project3.repository.AdviserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdviserService {

    private final AdviserRepository adviserRepository;

    public List<Adviser> getAllAdvisers() {
        return adviserRepository.findAll();
    }

    public Adviser getAdviserById(Long id) {
        return adviserRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Adviser not found with id: " + id));
    }

    public Adviser createAdviser(Adviser adviser) {
        return adviserRepository.save(adviser);
    }

    public Adviser updateAdviser(Long id, Adviser updatedAdviser) {
        Adviser existingAdviser = getAdviserById(id);

        existingAdviser.setName(updatedAdviser.getName());
        existingAdviser.setDepartment(updatedAdviser.getDepartment());

        return adviserRepository.save(existingAdviser);
    }

    public void deleteAdviser(Long id) {
        Adviser existingAdviser = getAdviserById(id);
        adviserRepository.delete(existingAdviser);
    }
}