package com.saeed.moviesplatform.rating.service;

import com.saeed.moviesplatform.entity.Rating;

public interface IRatingService {


    Rating rateMovie(Long userId, Long movieId, Integer rate);
}
