package com.saeed.moviesplatform.genre.service;

import com.saeed.moviesplatform.dto.GenreDto;
import com.saeed.moviesplatform.entity.Genre;

import java.util.List;

public interface IGenreService {

    Genre createGenre(String name);

    List<Genre> getAllGenres();

    void deleteGenre(Long id);

    Genre updateGenre(GenreDto request, Long id);
}
