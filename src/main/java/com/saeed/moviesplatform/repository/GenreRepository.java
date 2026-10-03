package com.saeed.moviesplatform.repository;

import com.saeed.moviesplatform.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre,Long> {
}
