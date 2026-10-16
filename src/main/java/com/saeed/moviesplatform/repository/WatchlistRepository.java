package com.saeed.moviesplatform.repository;

import com.saeed.moviesplatform.entity.Watchlist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WatchlistRepository extends JpaRepository<Watchlist, Long> {


    boolean existsByUser_IdAndMovie_Id(Long userID, Long movieId);

    List<Watchlist> findAllByUser_Id(Long userId);

    Optional<Watchlist> findByUser_IdAndMovie_Id(Long userId, Long movieId);

    void deleteAllByMovie_Id(Long movieId);
}