package com.saeed.moviesplatform.dto;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link com.saeed.moviesplatform.entity.Movie}
 */
public record CreateMovieRequest(String title, String description, String trailer, String poster, LocalDate releaseDate,
                                 Integer duration) {
}