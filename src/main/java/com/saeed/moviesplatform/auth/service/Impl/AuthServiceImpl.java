package com.saeed.moviesplatform.auth.service.Impl;

import com.saeed.moviesplatform.auth.service.IAuthService;
import com.saeed.moviesplatform.dto.RegisterRequest;
import com.saeed.moviesplatform.dto.RegisterResponse;
import com.saeed.moviesplatform.entity.PlatformUser;
import com.saeed.moviesplatform.entity.Role;
import com.saeed.moviesplatform.exception.exceptions.EmailAlreadyExistsException;
import com.saeed.moviesplatform.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements IAuthService {


    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

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
}
