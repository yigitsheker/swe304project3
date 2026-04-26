package com.example.swe304project3.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Table(name = "supervisions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Supervision {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Adviser is required")
    @ManyToOne
    @JoinColumn(name = "adviser_id", nullable = false)
    private Adviser adviser;

    @NotNull(message = "Study is required")
    @ManyToOne
    @JoinColumn(name = "study_id", nullable = false)
    private Study study;

    @NotBlank(message = "Student cannot be empty")
    @Size(max = 32, message = "Student can be maximum 32 characters")
    @Column(nullable = false, length = 32)
    private String student;

    @NotBlank(message = "Performance cannot be empty")
    @Size(max = 32, message = "Performance can be maximum 32 characters")
    @Column(nullable = false, length = 32)
    private String performance;
}