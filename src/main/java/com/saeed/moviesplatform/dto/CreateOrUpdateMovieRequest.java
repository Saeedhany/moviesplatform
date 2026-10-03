package com.saeed.moviesplatform.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

/**
 * DTO for {@link com.saeed.moviesplatform.entity.Movie}
 */
public record CreateOrUpdateMovieRequest(String title, String description, String trailer, String poster, LocalDate releaseDate,
                                         Integer duration, Set<Long> genreIds) {
}