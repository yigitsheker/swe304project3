package com.example.swe304project3.controller;

import com.example.swe304project3.entity.Adviser;
import com.example.swe304project3.service.AdviserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/advisers")
@RequiredArgsConstructor
public class AdviserController {

    private final AdviserService adviserService;

    @GetMapping
    public List<Adviser> getAllAdvisers() {
        return adviserService.getAllAdvisers();
    }

    @GetMapping("/{id}")
    public Adviser getAdviserById(@PathVariable Long id) {
        return adviserService.getAdviserById(id);
    }

    @PostMapping
    public Adviser createAdviser(@Valid @RequestBody Adviser adviser) {
        return adviserService.createAdviser(adviser);
    }

    @PutMapping("/{id}")
    public Adviser updateAdviser(@PathVariable Long id, @Valid @RequestBody Adviser adviser) {
        return adviserService.updateAdviser(id, adviser);
    }

    @DeleteMapping("/{id}")
    public String deleteAdviser(@PathVariable Long id) {
        adviserService.deleteAdviser(id);
        return "Adviser deleted successfully with id: " + id;
    }
}