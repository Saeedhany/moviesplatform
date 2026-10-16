package com.saeed.moviesplatform.watchlist.service;

import com.saeed.moviesplatform.dto.WatchlistDetailsResponse;
import com.saeed.moviesplatform.dto.WatchlistResponse;
import org.springframework.security.core.Authentication;

public interface IWatchlistService {


   WatchlistResponse addToWatchlist(Long userId, Long movieId);

   WatchlistDetailsResponse displayWatchlist(Long userId);

   void removeFromWatchlist(Long user_Id, Long movie_Id);

}
