package com.saeed.moviesplatform.actor.service.impl;

import com.saeed.moviesplatform.actor.service.IActorService;
import com.saeed.moviesplatform.dto.ActorDto;
import com.saeed.moviesplatform.entity.Actor;
import com.saeed.moviesplatform.exception.exceptions.ActorNotFoundException;
import com.saeed.moviesplatform.repository.ActorRepository;
import com.saeed.moviesplatform.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ActorServiceImpl implements IActorService {

    private final ActorRepository actorRepository;
    private final MovieRepository movieRepository;

    @Override
    public Actor createActor(ActorDto request) {
        Actor actor = new Actor();

        BeanUtils.copyProperties(request, actor);

        Actor savedActor = actorRepository.save(actor);

        return savedActor;
    }

    @Override
    public List<Actor> getAllActors() {

        List<Actor> actors = actorRepository.findAll();

        return actors;

    }

    @Override
    public Actor updateActor(Long id, ActorDto request) {

        Actor actor =  actorRepository.findById(id)
                .orElseThrow(() ->
                        new ActorNotFoundException(
                                "Actor not found with id: " + id
                        ));

        BeanUtils.copyProperties(request, actor);

        Actor savedActor = actorRepository.save(actor);

        return savedActor;
    }

    @Transactional
    @Override
    public void deleteActor(Long id) {

        actorRepository.findById(id)
                .orElseThrow(() ->
                        new ActorNotFoundException(
                                "Actor not found with id: " + id
                        ));

        movieRepository.deleteActorRelationships(id);

        actorRepository.deleteById(id);

    }
}
