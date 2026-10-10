package com.saeed.moviesplatform.dto;

import com.saeed.moviesplatform.entity.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

/**
 * DTO for {@link com.saeed.moviesplatform.entity.PlatformUser}
 */
public record UserResponse(@NotNull Long id,
                           @NotBlank String name,
                           @Email @NotBlank String email,
                           @NotNull Role role)  {
}