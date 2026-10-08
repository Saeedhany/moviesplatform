package com.saeed.moviesplatform.repository;

import com.saeed.moviesplatform.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RatingRepository extends JpaRepository<Rating, Long> {

    Optional<Rating> findByUser_IdAndMovie_Id(Long userId, Long movied);
}