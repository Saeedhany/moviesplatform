package com.saeed.moviesplatform.dto;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

/**
 * DTO for {@link Rating}
 */
public record RatingRequest(
        @NotNull
        @Min(1)
        @Max(5)
        Integer rate) {
}