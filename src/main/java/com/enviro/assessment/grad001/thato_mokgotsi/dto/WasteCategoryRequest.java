package com.enviro.assessment.grad001.thato_mokgotsi.dto;

import jakarta.validation.constraints.NotBlank;

public class WasteCategoryRequest {

    @NotBlank(message = "Category name is required")
    private String name;

    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}