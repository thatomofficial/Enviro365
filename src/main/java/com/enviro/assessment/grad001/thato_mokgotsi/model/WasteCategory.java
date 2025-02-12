package com.enviro.assessment.grad001.thato_mokgotsi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/**
 * Entity class for waste category
 */
@Getter
@Setter
@Entity
public class WasteCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Primary key

    @NotBlank(message = "Category name is required")
    private String name; // Name of the waste category

    private String description; // Description of the waste category

}
