package com.enviro.assessment.grad001.thato_mokgotsi.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * DTO for disposal guidelines response.
 */
@Getter
@Setter
public class DisposalGuidelinesResponse {
    private Long id; // Primary key
    private String guideline; // Disposal guideline
}