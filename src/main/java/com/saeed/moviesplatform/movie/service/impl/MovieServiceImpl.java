package com.saeed.moviesplatform.movie.service.impl;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import com.saeed.moviesplatform.dto.CreateOrUpdateMovieRequest;
import com.saeed.moviesplatform.entity.Movie;
import com.saeed.moviesplatform.exception.exceptions.MovieNotFoundException;
import com.saeed.moviesplatform.movie.service.IMovieService;
import com.saeed.moviesplatform.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements IMovieService {

    private final MovieRepository movieRepository;

    @Override
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    @Override
    public Movie createMovie(CreateOrUpdateMovieRequest createMovieRequest) {
        Movie movie = new Movie();
        BeanUtils.copyProperties(createMovieRequest, movie);

        Movie savedMovie = movieRepository.save(movie);

        return savedMovie;
    }

    @Override
    public Movie updateMovie(Long id, CreateOrUpdateMovieRequest request) {

        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new MovieNotFoundException("Movie not found with id: " + id));

        BeanUtils.copyProperties(request, movie);

        return movieRepository.save(movie);
    }

    @Override
    public void deleteMovie(Long id) {

        movieRepository.findById(id)
                .orElseThrow(() -> new MovieNotFoundException("Movie not found with id: " + id));

        movieRepository.deleteById(id);

    }

}
