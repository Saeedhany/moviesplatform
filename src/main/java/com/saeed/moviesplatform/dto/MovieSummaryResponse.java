package com.saeed.moviesplatform.dto;

public record MovieSummaryResponse(

        Long movieId,
        String title,
        Double rating
) {
}
