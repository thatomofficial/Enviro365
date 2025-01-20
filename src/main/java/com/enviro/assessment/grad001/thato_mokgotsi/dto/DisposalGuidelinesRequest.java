package com.enviro.assessment.grad001.thato_mokgotsi.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

/**
 * DTO for disposal guidelines request.
 */
@Getter
public class DisposalGuidelinesRequest {
    @NotBlank(message = "Guideline is required")
    private String guideline;

}