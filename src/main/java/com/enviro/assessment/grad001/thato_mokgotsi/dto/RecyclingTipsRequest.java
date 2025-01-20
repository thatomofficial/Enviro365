package com.enviro.assessment.grad001.thato_mokgotsi.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

/**
 * Request class for recycling tips
 */
@Getter
public class RecyclingTipsRequest {
    @NotBlank(message = "Tip is required")
    private String tip; // Recycling tip
}