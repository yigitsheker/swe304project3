package com.example.swe304project3.controller;

import com.example.swe304project3.entity.Supervision;
import com.example.swe304project3.service.SupervisionService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/supervisions")
@RequiredArgsConstructor
public class SupervisionController {

    private final SupervisionService supervisionService;

    @GetMapping
    public List<Supervision> getAllSupervisions() {
        return supervisionService.getAllSupervisions();
    }

    @GetMapping("/{id}")
    public Supervision getSupervisionById(@PathVariable Long id) {
        return supervisionService.getSupervisionById(id);
    }

    @PostMapping
    public Supervision createSupervision(@RequestBody SupervisionRequest request) {
        return supervisionService.createSupervision(
                request.getAdviserId(),
                request.getStudyId(),
                request.getStudent(),
                request.getPerformance()
        );
    }

    @PutMapping("/{id}")
    public Supervision updateSupervision(@PathVariable Long id, @RequestBody SupervisionRequest request) {
        return supervisionService.updateSupervision(
                id,
                request.getAdviserId(),
                request.getStudyId(),
                request.getStudent(),
                request.getPerformance()
        );
    }

    @DeleteMapping("/{id}")
    public String deleteSupervision(@PathVariable Long id) {
        supervisionService.deleteSupervision(id);
        return "Supervision deleted successfully with id: " + id;
    }

    @Data
    public static class SupervisionRequest {
        private Long adviserId;
        private Long studyId;
        private String student;
        private String performance;
    }
}