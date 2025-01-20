package com.enviro.assessment.grad001.thato_mokgotsi.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Response class for recycling tips
 */
@Getter
@Setter
public class RecyclingTipsResponse {
    private Long id; // Primary key
    private String tip; // Recycling tip
}