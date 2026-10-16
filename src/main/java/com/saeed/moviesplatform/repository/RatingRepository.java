package com.saeed.moviesplatform.repository;

import com.saeed.moviesplatform.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RatingRepository extends JpaRepository<Rating, Long> {

    Optional<Rating> findByUser_IdAndMovie_Id(Long userId, Long movied);

    @Query("SELECT AVG(r.rate) FROM Rating r WHERE r.movie.id = :movieId")
    Double findAverageRatingByMovie_Id(Long movieId);

    Long countByMovie_Id(Long movieId);


    @Query("""
    SELECT r.movie.id, AVG(r.rate)
    FROM Rating r
    WHERE r.movie.id IN :movieIds
    GROUP BY r.movie.id
    """)
    List<Object[]> findAverageRatingsByMovieIds(
            @Param("movieIds") List<Long> movieIds
    );

    void deleteAllByMovie_Id(Long movieId);
}