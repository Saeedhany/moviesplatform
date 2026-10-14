package com.saeed.moviesplatform.auth.service;

import com.saeed.moviesplatform.dto.LoginRequest;
import com.saeed.moviesplatform.dto.LoginResponse;
import com.saeed.moviesplatform.dto.RegisterRequest;
import com.saeed.moviesplatform.dto.RegisterResponse;

public interface IAuthService {



    RegisterResponse register(RegisterRequest request);

    LoginResponse login(LoginRequest request);

    void logout (String authHeader);

}
