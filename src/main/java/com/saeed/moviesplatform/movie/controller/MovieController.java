package com.saeed.moviesplatform.movie.controller;


import com.saeed.moviesplatform.dto.CreateMovieRequest;
import com.saeed.moviesplatform.entity.Movie;
import com.saeed.moviesplatform.movie.service.IMovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MovieController {

    private final IMovieService movieService;


    @GetMapping("/api/v1/movies")
    public List<Movie> getAllMovies(){
        return movieService.getAllMovies();
    }

    @PostMapping("/api/v1/movies")
    public Movie createMovie(@RequestBody CreateMovieRequest request){
        
        return movieService.createMovie(request);

    }
}
