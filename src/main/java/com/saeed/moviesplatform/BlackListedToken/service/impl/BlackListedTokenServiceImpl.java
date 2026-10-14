package com.saeed.moviesplatform.BlackListedToken.service.impl;

import com.saeed.moviesplatform.BlackListedToken.service.IBlackListedTokenService;
import com.saeed.moviesplatform.entity.BlacklistedToken;
import com.saeed.moviesplatform.repository.BlacklistedTokenRepository;
import com.saeed.moviesplatform.security.util.JwtService;
import com.saeed.moviesplatform.security.util.TokenHashUtil;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.Date;
import java.util.HexFormat;


@Service
@RequiredArgsConstructor
public class BlackListedTokenServiceImpl implements IBlackListedTokenService {

    private final BlacklistedTokenRepository blacklistedTokenRepository;

    private final JwtService jwtService;

    @Override
    public boolean isBlackListed(String tokenHash) {
        return blacklistedTokenRepository.existsByTokenHash(tokenHash);
    }

    @Override
    public void blacklistToken(String token) {

        Claims claims = jwtService.getClaims(token);

        Date expirationDate = claims.getExpiration();

        Instant expiresAt = expirationDate.toInstant();


            String tokenHash = TokenHashUtil.hashToken(token);

            BlacklistedToken blacklistedToken = new BlacklistedToken();

            blacklistedToken.setTokenHash(tokenHash);
            blacklistedToken.setExpiresAt(expiresAt);

            blacklistedTokenRepository.save(blacklistedToken);

        }



}
