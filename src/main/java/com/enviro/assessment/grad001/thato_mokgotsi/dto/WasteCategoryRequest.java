package com.enviro.assessment.grad001.thato_mokgotsi.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

/**
 * Request class for waste category
 */
@Getter
public class WasteCategoryRequest {

    @NotBlank(message = "Category name is required")
    private String name; // Name of the waste category

    private String description; // Description of the waste category

}