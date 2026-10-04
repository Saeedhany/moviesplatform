package com.saeed.moviesplatform.repository;

import com.saeed.moviesplatform.entity.Actor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActorRepository extends JpaRepository<Actor, Long> {
}