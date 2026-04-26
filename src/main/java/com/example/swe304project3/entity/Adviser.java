package com.example.swe304project3.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Table(name = "advisers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Adviser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Adviser name cannot be empty")
    @Size(max = 64, message = "Adviser name can be maximum 16 characters")
    @Column(nullable = false, length = 16)
    private String name;

    @NotBlank(message = "Department cannot be empty")
    @Size(max = 16, message = "Department can be maximum 16 characters")
    @Column(nullable = false, length = 16)
    private String department;
}