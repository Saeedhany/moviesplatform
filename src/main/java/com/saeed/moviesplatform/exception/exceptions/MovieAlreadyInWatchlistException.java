package com.saeed.moviesplatform.exception.exceptions;

public class MovieAlreadyInWatchlistException extends RuntimeException {
    public MovieAlreadyInWatchlistException(String message) {
        super(message);
    }
}
