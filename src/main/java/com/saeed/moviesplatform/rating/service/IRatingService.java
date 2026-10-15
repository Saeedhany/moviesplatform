package com.saeed.moviesplatform.rating.service;

import com.saeed.moviesplatform.dto.RatingResponse;
import com.saeed.moviesplatform.entity.Rating;

public interface IRatingService {


    RatingResponse rateMovie(Long userId, Long movieId, Integer rate);
}
