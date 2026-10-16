package com.saeed.moviesplatform.dto;

import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;

/**
 * DTO for {@link com.saeed.moviesplatform.entity.Genre}
 */
public record GenreDto(
        @NotBlank
        String name) {
}