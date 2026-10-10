package com.saeed.moviesplatform.auth.service.Impl;

import com.saeed.moviesplatform.auth.service.IAuthService;
import com.saeed.moviesplatform.dto.*;
import com.saeed.moviesplatform.entity.PlatformUser;
import com.saeed.moviesplatform.entity.Role;
import com.saeed.moviesplatform.exception.exceptions.EmailAlreadyExistsException;
import com.saeed.moviesplatform.repository.UserRepository;
import com.saeed.moviesplatform.security.CustomUserDetails;
import com.saeed.moviesplatform.security.util.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements IAuthService {


    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;


    @Override
    public RegisterResponse register(RegisterRequest request) {

        if(userRepository.existsByEmail(request.email())){

            throw new EmailAlreadyExistsException("Email already exists");
        }

        PlatformUser user = new PlatformUser();

        user.setName(request.name());
        user.setEmail(request.email());

        user.setPassword(passwordEncoder.encode(request.password()));
        user.setRole(Role.USER);

       PlatformUser savedUser = userRepository.save(user);

       RegisterResponse response = new RegisterResponse(
               savedUser.getId(),
               savedUser.getName(),
               savedUser.getEmail(),
               savedUser.getRole());

       return response;
    }

    @Override
    public LoginResponse login(LoginRequest request) {

        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.password()
                ));
        CustomUserDetails userDetails =
                (CustomUserDetails) authentication.getPrincipal();

        PlatformUser user = userDetails.getUser();

        String token = jwtService.generateToken(user);

        UserResponse userResponse = new UserResponse(user.getId(), user.getName(), user.getEmail(), user.getRole());

        LoginResponse response = new LoginResponse(token, userResponse);

        return response;
    }
}
