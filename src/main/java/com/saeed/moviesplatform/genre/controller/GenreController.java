package com.saeed.moviesplatform.genre.controller;


import com.saeed.moviesplatform.dto.GenreDto;
import com.saeed.moviesplatform.entity.Genre;
import com.saeed.moviesplatform.genre.service.IGenreService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/genres")
public class GenreController {

    private final IGenreService genreService;



    @GetMapping
    public List<Genre> getAllGenres(){
        return  genreService.getAllGenres();
    }

    @PostMapping
    public ResponseEntity<Genre> createGenre(@RequestBody @Valid GenreDto request){

      Genre genre = genreService.createGenre(request.name());

       return ResponseEntity
               .status(HttpStatus.CREATED)
               .body(genre);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Genre> updateGenre(@RequestBody @Valid GenreDto request,@PathVariable Long id){

        Genre genre = genreService.updateGenre(request,id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(genre);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGenre(@PathVariable Long id){
        genreService.deleteGenre(id);

       return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }








}
