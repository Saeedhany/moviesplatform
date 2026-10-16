package com.saeed.moviesplatform.dto;

import java.util.List;

public record WatchlistDetailsResponse(

        Long userId,
        List<MovieSummaryResponse> movieSummaryResponseList
) {
}
