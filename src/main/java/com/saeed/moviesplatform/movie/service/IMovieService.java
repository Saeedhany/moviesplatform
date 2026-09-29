package com.saeed.moviesplatform.movie.service;

import com.saeed.moviesplatform.dto.CreateMovieRequest;
import com.saeed.moviesplatform.entity.Movie;

import java.util.List;

public interface IMovieService {


    List<Movie> getAllMovies();

    Movie createMovie(CreateMovieRequest createMovieRequest);
}
