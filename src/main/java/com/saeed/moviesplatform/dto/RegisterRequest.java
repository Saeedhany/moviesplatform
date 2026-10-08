package com.saeed.moviesplatform.dto;

import com.saeed.moviesplatform.entity.PlatformUser;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;


/**
 * DTO for {@link PlatformUser}
 */
public record RegisterRequest(
        @NotBlank
        String name,
        @NotBlank
        @Email
        String email,
        @NotBlank
        String password){
}