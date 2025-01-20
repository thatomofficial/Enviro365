package com.enviro.assessment.grad001.thato_mokgotsi.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * DTO for waste category response.
 */
@Getter
@Setter
public class WasteCategoryResponse {
    private Long id; // Primary key
    private String name;// Name of the category
    private String description; // Description of the category
    

}
