package com.saeed.moviesplatform.repository;

import com.saeed.moviesplatform.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface RatingRepository extends JpaRepository<Rating, Long> {

    Optional<Rating> findByUser_IdAndMovie_Id(Long userId, Long movied);

    @Query("SELECT AVG(r.rate) FROM Rating r WHERE r.movie.id = :movieId")
    Double findAverageRatingByMovie_Id(Long movieId);

    Long countByMovie_Id(Long movieId);

}