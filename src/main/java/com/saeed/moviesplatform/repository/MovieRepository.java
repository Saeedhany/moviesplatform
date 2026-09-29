package com.saeed.moviesplatform.repository;

import com.saeed.moviesplatform.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}