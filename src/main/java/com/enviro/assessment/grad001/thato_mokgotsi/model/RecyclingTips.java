package com.enviro.assessment.grad001.thato_mokgotsi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/**
 * Entity class for Recycling Tips
 */
@Getter
@Setter
@Entity
public class RecyclingTips {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Primary key

    @NotBlank(message = "Tip is required")
    private String tip; // Recycling tip
}