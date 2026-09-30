package com.saeed.moviesplatform.dto;


public record ErrorResponseDto(
        String message,
        int status
) {}
