package com.saeed.moviesplatform.dto;

import java.io.Serializable;

/**
 * DTO for {@link com.saeed.moviesplatform.entity.Rating}
 */
public record RatingResponse(
        Long id,
        Long movieId,
        Long userId,
        Integer rate) implements Serializable {
}