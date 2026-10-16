package com.saeed.moviesplatform.repository;

import com.saeed.moviesplatform.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MovieRepository extends JpaRepository<Movie, Long>, JpaSpecificationExecutor<Movie> {

    @Modifying
    @Query(value = """
    DELETE FROM movie_actor
    WHERE actor_id = :actorId
    """, nativeQuery = true)
    void deleteActorRelationships(@Param("actorId") Long actorId);

    @Modifying
    @Query(value = """
    DELETE FROM movie_genre
    WHERE genre_id = :genreId
    """, nativeQuery = true)
    void deleteGenreRelationships(@Param("genreId") Long genreId);

}