package com.saeed.moviesplatform.dto;

import java.io.Serializable;

/**
 * DTO for {@link com.saeed.moviesplatform.entity.Watchlist}
 */
public record WatchlistResponse(Long id,
                                Long userId,
                                Long movieId
) {
}