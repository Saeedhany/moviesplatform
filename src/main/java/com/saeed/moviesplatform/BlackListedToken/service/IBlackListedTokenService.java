package com.saeed.moviesplatform.BlackListedToken.service;

public interface IBlackListedTokenService {


    boolean isBlackListed(String tokenHash);

    void blacklistToken(String token);
}
