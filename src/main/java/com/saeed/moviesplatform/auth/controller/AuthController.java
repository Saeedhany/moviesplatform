package com.saeed.moviesplatform.auth.controller;


import com.saeed.moviesplatform.auth.service.IAuthService;
import com.saeed.moviesplatform.dto.RegisterRequest;
import com.saeed.moviesplatform.dto.RegisterResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {


    private final IAuthService authService;


    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@RequestBody @Valid RegisterRequest request){

       return ResponseEntity
               .status(HttpStatus.CREATED)
               .body(authService.register(request));
    }



}
