package com.enviro.assessment.grad001.thato_mokgotsi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/**
 * Entity class for Disposal Guidelines
 */
@Getter
@Setter
@Entity
public class DisposalGuidelines {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Primary key

    @NotBlank(message = "Guideline is required")
    private String guideline; // Disposal guideline
}