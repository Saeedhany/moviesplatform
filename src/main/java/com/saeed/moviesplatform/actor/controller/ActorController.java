package com.saeed.moviesplatform.actor.controller;


import com.saeed.moviesplatform.actor.service.IActorService;
import com.saeed.moviesplatform.dto.ActorDto;
import com.saeed.moviesplatform.entity.Actor;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/actors")
public class ActorController {

    private final IActorService actorService;

    @GetMapping
    public List<Actor> getAllActors(){

       List<Actor> actors =  actorService.getAllActors();

       return actors;
    }

    @SecurityRequirement(name = "bearerAuth")
    @PostMapping
    public ResponseEntity<Actor> createActor(@RequestBody @Valid ActorDto request){

        Actor actor = actorService.createActor(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(actor);

    }

    @SecurityRequirement(name = "bearerAuth")
    @PutMapping("/{id}")
    public ResponseEntity<Actor> updateActor(@RequestBody @Valid ActorDto request,  @PathVariable Long id){

        Actor actor = actorService.updateActor(id , request);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(actor);
    }

    @SecurityRequirement(name = "bearerAuth")
    @DeleteMapping("/{id}")
    public ResponseEntity <Void> deleteActor(@PathVariable Long id){

        actorService.deleteActor(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
