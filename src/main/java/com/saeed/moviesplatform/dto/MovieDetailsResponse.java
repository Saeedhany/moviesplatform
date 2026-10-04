package com.saeed.moviesplatform.dto;

import java.time.LocalDate;
import java.util.Set;

/**
 * DTO for {@link com.saeed.moviesplatform.entity.Movie}
 */
public record MovieDetailsResponse(Long id,
                                   String title,
                                   String description,
                                   String trailer,
                                   String poster,
                                   LocalDate releaseDate,
                                   Integer duration,
                                   Set<GenreDto> genres,
                                   Set<ActorDto> actors)  {
}