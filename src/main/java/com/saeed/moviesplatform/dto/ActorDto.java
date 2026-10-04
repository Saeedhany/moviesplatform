package com.saeed.moviesplatform.dto;

import java.io.Serializable;

/**
 * DTO for {@link com.saeed.moviesplatform.entity.Actor}
 */
public record ActorDto(String name, String photo, String biography)  {
}