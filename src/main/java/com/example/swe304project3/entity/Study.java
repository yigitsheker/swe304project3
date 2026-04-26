package com.example.swe304project3.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Table(name = "studies")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Study {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Study title cannot be empty")
    @Size(max = 16, message = "Study title can be maximum 16 characters")
    @Column(nullable = false, length = 16)
    private String title;

    @NotBlank(message = "Description cannot be empty")
    @Size(max = 64, message = "Description can be maximum 64 characters")
    @Column(nullable = false, length = 64)
    private String description;
}