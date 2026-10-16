package com.saeed.moviesplatform.movie.controller;


import com.saeed.moviesplatform.dto.CreateOrUpdateMovieRequest;
import com.saeed.moviesplatform.dto.MovieDetailsResponse;
import com.saeed.moviesplatform.dto.RatingRequest;
import com.saeed.moviesplatform.dto.RatingResponse;
import com.saeed.moviesplatform.entity.Movie;
import com.saeed.moviesplatform.entity.Rating;
import com.saeed.moviesplatform.movie.service.IMovieService;
import com.saeed.moviesplatform.rating.service.IRatingService;
import com.saeed.moviesplatform.security.CustomUserDetails;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/movies")

public class MovieController {

    private final IMovieService movieService;
    private final IRatingService ratingService;


    @PostMapping()
    public ResponseEntity<Movie> createMovie(@RequestBody CreateOrUpdateMovieRequest request){
        
        Movie movie = movieService.createMovie(request);

       return ResponseEntity
               .status(HttpStatus.CREATED)
               .body(movie);
    }

    @PutMapping("/{id}")
    public Movie updateMovie(@PathVariable String id, @RequestBody CreateOrUpdateMovieRequest updateMovieRequest){

         return movieService.updateMovie(Long.valueOf(id), updateMovieRequest);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable String id){
        movieService.deleteMovie(Long.valueOf(id));

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieDetailsResponse> getMovieDetails(@PathVariable Long id){

        MovieDetailsResponse movieDetails =  movieService.getMovieById(id);

        return ResponseEntity.ok(movieDetails);

    }

    @GetMapping
    public List<Movie> getMovies(@RequestParam(required = false) String title, @RequestParam(required = false) Long genreId,@RequestParam(required = false) LocalDate dateFrom,@RequestParam(required = false) LocalDate dateTo, @RequestParam(required = false) Double minRating){

        return movieService.searchMovies(title,genreId,dateFrom,dateTo, minRating);
    }

    @PostMapping("/{movieId}/rating")
    public RatingResponse rateMovie(@PathVariable Long movieId,
                                    @Valid @RequestBody RatingRequest ratingRequest, Authentication authentication){

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        Long userId = userDetails.getUser().getId();

        return ratingService.rateMovie(userId, movieId, ratingRequest.rate());

    }
}
