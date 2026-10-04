package com.saeed.moviesplatform.movie.service.impl;

import com.saeed.moviesplatform.dto.CreateOrUpdateMovieRequest;
import com.saeed.moviesplatform.entity.Actor;
import com.saeed.moviesplatform.entity.Genre;
import com.saeed.moviesplatform.entity.Movie;
import com.saeed.moviesplatform.exception.exceptions.ActorNotFoundException;
import com.saeed.moviesplatform.exception.exceptions.GenreNotFoundException;
import com.saeed.moviesplatform.exception.exceptions.MovieNotFoundException;
import com.saeed.moviesplatform.movie.service.IMovieService;
import com.saeed.moviesplatform.repository.ActorRepository;
import com.saeed.moviesplatform.repository.GenreRepository;
import com.saeed.moviesplatform.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements IMovieService {

    private final MovieRepository movieRepository;

    private final GenreRepository genreRepository;

    private final ActorRepository actorRepository;

    @Override
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    @Override
    public Movie createMovie(CreateOrUpdateMovieRequest createMovieRequest) {
        Movie movie = new Movie();

        return prepareAndSaveMovie(createMovieRequest, movie);
    }

    @Override
    public Movie updateMovie(Long id, CreateOrUpdateMovieRequest request) {

        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new MovieNotFoundException("Movie not found with id: " + id));

        return prepareAndSaveMovie(request, movie);
    }

    @Override
    public void deleteMovie(Long id) {

        movieRepository.findById(id)
                .orElseThrow(() -> new MovieNotFoundException("Movie not found with id: " + id));

        movieRepository.deleteById(id);

    }


    private Movie prepareAndSaveMovie(CreateOrUpdateMovieRequest request, Movie movie){

        BeanUtils.copyProperties(request, movie);

        Set<Genre> genres =
                new HashSet<>(genreRepository.findAllById(request.genreIds()));

        if(genres.size() != request.genreIds().size()){
            throw new GenreNotFoundException(
                    "One or more genres were not found"
            );
        }

        Set<Actor> actors =
                new HashSet<>(actorRepository.findAllById(request.actorIds()));

        if(actors.size() != request.actorIds().size()){
            throw new ActorNotFoundException(
                    "One or more actors were not found"
            );
        }

        movie.setGenres(genres);
        movie.setActors(actors);

        Movie savedMovie = movieRepository.save(movie);

        return savedMovie;
    }

}
