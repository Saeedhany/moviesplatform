package com.saeed.moviesplatform.movie.service.impl;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import com.saeed.moviesplatform.dto.CreateMovieRequest;
import com.saeed.moviesplatform.entity.Movie;
import com.saeed.moviesplatform.movie.service.IMovieService;
import com.saeed.moviesplatform.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements IMovieService {

    private final MovieRepository movieRepository;

    @Override
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    @Override
    public Movie createMovie(CreateMovieRequest createMovieRequest) {
        Movie movie = new Movie();
        BeanUtils.copyProperties(createMovieRequest, movie);

        Movie savedMovie = movieRepository.save(movie);

        return savedMovie;
    }
}
