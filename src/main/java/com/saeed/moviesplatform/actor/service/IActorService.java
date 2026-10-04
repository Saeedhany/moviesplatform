package com.saeed.moviesplatform.actor.service;

import com.saeed.moviesplatform.dto.ActorDto;
import com.saeed.moviesplatform.entity.Actor;

import java.util.List;

public interface IActorService {

    Actor createActor(ActorDto request);

    List<Actor> getAllActors();

    Actor updateActor(Long id, ActorDto request);

    void deleteActor(Long id);
}
