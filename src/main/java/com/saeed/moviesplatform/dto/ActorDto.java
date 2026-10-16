package com.saeed.moviesplatform.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

/**
 * DTO for {@link com.saeed.moviesplatform.entity.Actor}
 */
public record ActorDto(
        @NotBlank
        String name, String photo, String biography)  {
}