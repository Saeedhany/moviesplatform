package com.saeed.moviesplatform.repository;

import com.saeed.moviesplatform.entity.PlatformUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<PlatformUser, Long> {

    boolean existsByEmail(String email);
}