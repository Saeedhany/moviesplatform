package com.saeed.moviesplatform.movie.service;

import com.saeed.moviesplatform.dto.CreateOrUpdateMovieRequest;
import com.saeed.moviesplatform.dto.MovieDetailsResponse;
import com.saeed.moviesplatform.entity.Movie;

import java.util.List;

public interface IMovieService {


    List<Movie> getAllMovies();

    Movie createMovie(CreateOrUpdateMovieRequest createMovieRequest);

    Movie updateMovie(Long id,CreateOrUpdateMovieRequest updateMovieRequest);

    void deleteMovie(Long id);

    MovieDetailsResponse getMovieById(Long id);
}
