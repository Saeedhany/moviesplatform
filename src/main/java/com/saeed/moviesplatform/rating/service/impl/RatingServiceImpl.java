package com.saeed.moviesplatform.rating.service.impl;


import com.saeed.moviesplatform.entity.Movie;
import com.saeed.moviesplatform.entity.Rating;
import com.saeed.moviesplatform.entity.PlatformUser;
import com.saeed.moviesplatform.exception.exceptions.MovieNotFoundException;
import com.saeed.moviesplatform.exception.exceptions.UserNotFoundException;
import com.saeed.moviesplatform.rating.service.IRatingService;
import com.saeed.moviesplatform.repository.MovieRepository;
import com.saeed.moviesplatform.repository.RatingRepository;
import com.saeed.moviesplatform.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RatingServiceImpl implements IRatingService {

    private final RatingRepository ratingRepository;
    private final MovieRepository movieRepository;
    private final UserRepository userRepository;

    @Override
    public Rating rateMovie(Long userId, Long movieId, Integer rate) {
        Rating rating = new Rating();

        Optional<Rating> existingRating =
                ratingRepository.findByUser_IdAndMovie_Id(userId, movieId);

        if(existingRating.isEmpty()){

            Movie movie = movieRepository.findById(movieId)
                    .orElseThrow( ()-> new MovieNotFoundException("Not Found Movie with Id: "+ movieId));

            PlatformUser user = userRepository.findById(userId)
                    .orElseThrow(() -> new UserNotFoundException("Not Found User with Id: " + userId));

            rating.setMovie(movie);
            rating.setUser(user);
            rating.setRate(rate);


        } else {
            rating = existingRating.get();

            rating.setRate(rate);

        }

        return ratingRepository.save(rating);

    }
}
