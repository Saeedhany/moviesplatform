package com.saeed.moviesplatform.exception;


import com.saeed.moviesplatform.dto.ErrorResponseDto;
import com.saeed.moviesplatform.exception.exceptions.*;
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

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleUserNotFound(UserNotFoundException exception){

        ErrorResponseDto errorResponse = new ErrorResponseDto(exception.getMessage(), 404);

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(errorResponse);
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDto> handleEmailAlreadyExists(EmailAlreadyExistsException exception){

        ErrorResponseDto errorResponse = new ErrorResponseDto(exception.getMessage(), 409);

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(errorResponse);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponseDto> handleIllegalArgument(
            IllegalArgumentException exception) {

        ErrorResponseDto errorResponse =
                new ErrorResponseDto(exception.getMessage(), 400);

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorResponse);
    }

    @ExceptionHandler(MovieAlreadyInWatchlistException.class)
    public ResponseEntity<ErrorResponseDto> handleMovieAlreadyExists(MovieAlreadyInWatchlistException exception){

        ErrorResponseDto errorResponse = new ErrorResponseDto(exception.getMessage(), 409);

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(errorResponse);
    }

}
