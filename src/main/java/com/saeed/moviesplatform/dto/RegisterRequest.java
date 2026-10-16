package com.saeed.moviesplatform.dto;

import com.saeed.moviesplatform.entity.PlatformUser;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


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
        @Size(min = 8)
        String password){
}