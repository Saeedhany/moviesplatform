package com.saeed.moviesplatform.exception;


import com.saeed.moviesplatform.dto.ErrorResponseDto;
import com.saeed.moviesplatform.exception.exceptions.ActorNotFoundException;
import com.saeed.moviesplatform.exception.exceptions.GenreNotFoundException;
import com.saeed.moviesplatform.exception.exceptions.MovieNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    @ExceptionHandler(MovieNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleMovieNotFound(MovieNotFoundException exception){

        ErrorResponseDto errorResponse = new ErrorResponseDto(exception.getMessage(), 404);

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(errorResponse);
    }

    @ExceptionHandler(GenreNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleGenreNotFound(GenreNotFoundException exception){

        ErrorResponseDto errorResponse = new ErrorResponseDto(exception.getMessage(), 404);

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(errorResponse);
    }

    @ExceptionHandler(ActorNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleActorNotFound(ActorNotFoundException exception){

        ErrorResponseDto errorResponse = new ErrorResponseDto(exception.getMessage(), 404);

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(errorResponse);
    }

}
