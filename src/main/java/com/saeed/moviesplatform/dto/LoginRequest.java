package com.saeed.moviesplatform.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;

/**
 * DTO for {@link com.saeed.moviesplatform.entity.PlatformUser}
 */
public record LoginRequest(
        @Email @NotBlank String email,
        @NotBlank String password){
}