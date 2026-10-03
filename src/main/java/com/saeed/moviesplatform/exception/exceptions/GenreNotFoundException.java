package com.saeed.moviesplatform.exception.exceptions;

public class GenreNotFoundException extends RuntimeException{

    public GenreNotFoundException(String message){
        super(message);
    }
}
