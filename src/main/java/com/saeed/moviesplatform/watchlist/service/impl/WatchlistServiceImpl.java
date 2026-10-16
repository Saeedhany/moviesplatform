package com.saeed.moviesplatform.watchlist.service.impl;

import com.saeed.moviesplatform.dto.MovieSummaryResponse;
import com.saeed.moviesplatform.dto.WatchlistDetailsResponse;
import com.saeed.moviesplatform.dto.WatchlistResponse;
import com.saeed.moviesplatform.entity.Movie;
import com.saeed.moviesplatform.entity.PlatformUser;
import com.saeed.moviesplatform.entity.Watchlist;
import com.saeed.moviesplatform.exception.exceptions.MovieAlreadyInWatchlistException;
import com.saeed.moviesplatform.exception.exceptions.MovieNotFoundException;
import com.saeed.moviesplatform.exception.exceptions.UserNotFoundException;
import com.saeed.moviesplatform.repository.MovieRepository;
import com.saeed.moviesplatform.repository.RatingRepository;
import com.saeed.moviesplatform.repository.UserRepository;
import com.saeed.moviesplatform.repository.WatchlistRepository;
import com.saeed.moviesplatform.watchlist.service.IWatchlistService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class WatchlistServiceImpl implements IWatchlistService {


    private final WatchlistRepository watchlistRepository;
    private final MovieRepository movieRepository;
    private final UserRepository userRepository;
    private final RatingRepository ratingRepository;

    @Override
    public WatchlistResponse addToWatchlist(Long userId, Long movieId) {

       Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new MovieNotFoundException("Not Found Movie with id: " + movieId));

        PlatformUser user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("Not Found User with id:" + userId));

        boolean movieExists =  watchlistRepository.existsByUser_IdAndMovie_Id(userId, movieId);

        if (movieExists){
            throw new MovieAlreadyInWatchlistException("Movie is already found in your watchlist, movie id: " + movieId);
        }

        Watchlist watchlist = new Watchlist();

        watchlist.setMovie(movie);
        watchlist.setUser(user);


        Watchlist savedWatchlist =  watchlistRepository.save(watchlist);


        return transformWatchlistToWatchlistResponse(savedWatchlist);



    }


    @Override
    public WatchlistDetailsResponse displayWatchlist(Long userId) {

        List<Watchlist> watchlistDetails = watchlistRepository.findAllByUser_Id(userId);


       return transformToDisplayWatchlistResponse(userId, watchlistDetails);



    }

    @Override
    public void removeFromWatchlist(Long user_Id, Long movie_Id) {

        Watchlist watchlist =
                watchlistRepository.findByUser_IdAndMovie_Id(user_Id, movie_Id)
                        .orElseThrow(() -> new MovieNotFoundException("You do not have in your list movie with id: " + movie_Id));

        watchlistRepository.delete(watchlist);

    }

    private WatchlistResponse transformWatchlistToWatchlistResponse(Watchlist watchlist){

        WatchlistResponse watchlistResponse =
                new WatchlistResponse(
                        watchlist.getId(),
                        watchlist.getUser().getId(),
                        watchlist.getMovie().getId()
                );

        return watchlistResponse;
    }

    private WatchlistDetailsResponse transformToDisplayWatchlistResponse(Long userId, List<Watchlist> watchlistDetails){

        List<Long> movieIds = watchlistDetails.stream()
                .map(watchlist -> watchlist.getMovie().getId())
                .toList();

        Map<Long, Double> averageRatings = getAverageRatings(movieIds);

        List<MovieSummaryResponse> movies = watchlistDetails.stream()
                .map(watchlist -> {
                    Movie movie = watchlist.getMovie();

                    Long movieId = movie.getId();
                    String title = movie.getTitle();

                    Double rating =
                            averageRatings.get(movieId);

                    return new MovieSummaryResponse(movieId, title, rating);
                }).toList();

        WatchlistDetailsResponse watchlistResponse = new WatchlistDetailsResponse(userId,
                movies

                );

        return watchlistResponse;
    }


    private Map<Long, Double> getAverageRatings(List<Long> movieIds) {
        if (movieIds.isEmpty()) {
            return Map.of();
        }

        List<Object[]> ratingRows =
                ratingRepository.findAverageRatingsByMovieIds(movieIds);

        return ratingRows.stream()
                .collect(Collectors.toMap(
                        row -> (Long) row[0],
                        row -> ((Number) row[1]).doubleValue()
                ));
    }
}
