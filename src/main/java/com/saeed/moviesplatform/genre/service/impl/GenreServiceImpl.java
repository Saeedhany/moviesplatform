package com.saeed.moviesplatform.genre.service.impl;


import com.saeed.moviesplatform.dto.GenreDto;
import com.saeed.moviesplatform.entity.Genre;
import com.saeed.moviesplatform.exception.exceptions.GenreNotFoundException;
import com.saeed.moviesplatform.genre.service.IGenreService;
import com.saeed.moviesplatform.repository.GenreRepository;
import com.saeed.moviesplatform.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements IGenreService {

    private final GenreRepository genreRepository;

    private final MovieRepository movieRepository;

    @Override
    public Genre createGenre(String name) {
        Genre genre = new Genre();
        genre.setName(name);

        Genre savedGenre = genreRepository.save(genre);

        return savedGenre;
    }

    @Override
    public List<Genre> getAllGenres() {
       List<Genre> genres = genreRepository.findAll();

        return genres;
    }

    @Transactional
    @Override
    public void deleteGenre(Long id) {

        genreRepository.findById(id).
                orElseThrow(() -> new GenreNotFoundException("Genre Not Found"));

        movieRepository.deleteGenreRelationships(id);

        genreRepository.deleteById(id);
    }

    @Override
    public Genre updateGenre(GenreDto request, Long id) {
       Genre genre = genreRepository.findById(id).
                orElseThrow(() -> new GenreNotFoundException("Genre Not Found"));

        BeanUtils.copyProperties(request, genre);

        Genre savedGenre = genreRepository.save(genre);

        return savedGenre;

    }
}
