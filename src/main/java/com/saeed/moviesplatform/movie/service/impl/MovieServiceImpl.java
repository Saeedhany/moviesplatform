package com.saeed.moviesplatform.movie.service.impl;

import com.saeed.moviesplatform.dto.ActorDto;
import com.saeed.moviesplatform.dto.CreateOrUpdateMovieRequest;
import com.saeed.moviesplatform.dto.GenreDto;
import com.saeed.moviesplatform.dto.MovieDetailsResponse;
import com.saeed.moviesplatform.entity.Actor;
import com.saeed.moviesplatform.entity.Genre;
import com.saeed.moviesplatform.entity.Movie;
import com.saeed.moviesplatform.exception.exceptions.ActorNotFoundException;
import com.saeed.moviesplatform.exception.exceptions.GenreNotFoundException;
import com.saeed.moviesplatform.exception.exceptions.MovieNotFoundException;
import com.saeed.moviesplatform.movie.service.IMovieService;
import com.saeed.moviesplatform.repository.*;
import com.saeed.moviesplatform.specification.MovieSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements IMovieService {

    private final MovieRepository movieRepository;

    private final GenreRepository genreRepository;

    private final ActorRepository actorRepository;

    private final RatingRepository ratingRepository;

    private final WatchlistRepository watchlistRepository;

    @Override
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    @Transactional
    @Override
    public Movie createMovie(CreateOrUpdateMovieRequest createMovieRequest) {
        Movie movie = new Movie();

        return prepareAndSaveMovie(createMovieRequest, movie);
    }

    @Transactional
    @Override
    public Movie updateMovie(Long id, CreateOrUpdateMovieRequest request) {

        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new MovieNotFoundException("Movie not found with id: " + id));

        return prepareAndSaveMovie(request, movie);
    }

    @Transactional
    @Override
    public void deleteMovie(Long id) {

        movieRepository.findById(id)
                .orElseThrow(() -> new MovieNotFoundException("Movie not found with id: " + id));

        watchlistRepository.deleteAllByMovie_Id(id);

        movieRepository.deleteById(id);

    }

    @Override
    public MovieDetailsResponse getMovieById(Long id) {

        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new MovieNotFoundException("Movie not found with id: " + id));

        Double averageRating =
                ratingRepository.findAverageRatingByMovie_Id(id);

        Long ratingCount =
                ratingRepository.countByMovie_Id(id);

        return transformMovieToDetailsResponse(
                movie,
                averageRating,
                ratingCount
        );
    }

    @Override
    public Page<Movie> searchMovies(String title , Long genreId, LocalDate dateFrom, LocalDate dateTo, Double minRating, Pageable pageable) {

        Specification<Movie> specifications = null;

        if (title != null) {
            specifications = addSpecification(
                    specifications,
                    MovieSpecification.hasTitle(title)
            );
        }

        if (dateFrom != null) {
            specifications = addSpecification(
                    specifications,
                    MovieSpecification.hasReleaseDateFrom(dateFrom)
            );
        }
        
        if (genreId != null) {
            specifications = addSpecification(
                    specifications,
                    MovieSpecification.hasGenre(genreId)
            );
        }

        if (dateTo != null) {
            specifications = addSpecification(
                    specifications,
                    MovieSpecification.hasReleaseDateTo(dateTo)
            );
        }

        if (minRating != null) {
            specifications = addSpecification(
                    specifications,
                    MovieSpecification.hasMinimumRating(minRating)
            );
        }

        if (specifications == null) {
            return movieRepository.findAll(pageable);
        }

        return movieRepository.findAll(specifications, pageable);
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

    private MovieDetailsResponse transformMovieToDetailsResponse(Movie movie, Double averageRating, Long ratingCount){

        Set<GenreDto> genres = movie.getGenres()
                .stream()
                .map(genre -> new GenreDto(genre.getName()))
                .collect(Collectors.toSet());

        Set<ActorDto> actors = movie.getActors()
                .stream()
                .map(actor -> new ActorDto(actor.getName(), actor.getPhoto(), actor.getBiography()))
                .collect(Collectors.toSet());

          MovieDetailsResponse movieDetailsResponse = new MovieDetailsResponse(
                movie.getId(),
                movie.getTitle(),
                movie.getDescription(),
                movie.getTrailer(),
                movie.getPoster(),
                movie.getReleaseDate(),
                movie.getDuration(),
                genres,
                actors,
                averageRating,
                ratingCount
        );

       return movieDetailsResponse;
    }


    private Specification<Movie> addSpecification(Specification<Movie> current, Specification<Movie> newSpecification){

        if(current == null){
            return newSpecification;
        }

        return current.and(newSpecification);

    }
}
