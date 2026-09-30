package com.saeed.moviesplatform.movie.controller;


import com.saeed.moviesplatform.dto.CreateOrUpdateMovieRequest;
import com.saeed.moviesplatform.entity.Movie;
import com.saeed.moviesplatform.movie.service.IMovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/movies")

public class MovieController {

    private final IMovieService movieService;


    @GetMapping()
    public List<Movie> getAllMovies(){
        return movieService.getAllMovies();
    }

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
}
