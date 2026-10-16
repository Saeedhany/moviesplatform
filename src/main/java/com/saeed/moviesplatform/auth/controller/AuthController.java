package com.saeed.moviesplatform.auth.controller;


import com.saeed.moviesplatform.auth.service.IAuthService;
import com.saeed.moviesplatform.dto.LoginRequest;
import com.saeed.moviesplatform.dto.LoginResponse;
import com.saeed.moviesplatform.dto.RegisterRequest;
import com.saeed.moviesplatform.dto.RegisterResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest request){

        return ResponseEntity
                .ok(authService.login(request));
    }

    @SecurityRequirement(name = "bearerAuth")
    @PostMapping("/logout")
    public ResponseEntity<Void> logout(
            @RequestHeader(value = "Authorization", required = false) String authHeader){

        authService.logout(authHeader);

        return ResponseEntity.noContent().build();
    }



}
