package com.saeed.moviesplatform.repository;

import com.saeed.moviesplatform.entity.BlacklistedToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlacklistedTokenRepository extends JpaRepository<BlacklistedToken, Long> {

    boolean existsByTokenHash(String tokenHash);
}