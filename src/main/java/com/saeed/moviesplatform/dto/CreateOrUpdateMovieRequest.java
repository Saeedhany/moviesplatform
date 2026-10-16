package com.saeed.moviesplatform.dto;

import jakarta.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

/**
 * DTO for {@link com.saeed.moviesplatform.entity.Movie}
 */
public record CreateOrUpdateMovieRequest(
        @NotBlank
        String title,
        @NotBlank
        String description,
        String trailer,
        @NotBlank
        String poster,
        @NotNull @PastOrPresent
        LocalDate releaseDate,
        @NotNull@Positive
        Integer duration,
        Set<@NotNull@Positive Long> genreIds,
        Set<@NotNull@Positive Long> actorIds) {
}