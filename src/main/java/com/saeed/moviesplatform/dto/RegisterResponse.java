package com.saeed.moviesplatform.dto;

import com.saeed.moviesplatform.entity.PlatformUser;
import com.saeed.moviesplatform.entity.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


/**
 * DTO for {@link PlatformUser}
 */
public record RegisterResponse(
        @NotNull
        Long id,
        @NotBlank
        String name,
        @NotBlank
        @Email
        String email,
        @NotNull
        Role role
        ){
}