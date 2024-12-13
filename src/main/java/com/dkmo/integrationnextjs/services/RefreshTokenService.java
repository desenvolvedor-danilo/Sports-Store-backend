package com.dkmo.integrationnextjs.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dkmo.integrationnextjs.dto.TokenDto;
import com.dkmo.integrationnextjs.interfaces.IRefreshToken;
@Service
public class RefreshTokenService implements IRefreshToken{
    @Autowired
    private AuthenticatedUserService authenticatedService;
    @Override
    public TokenDto refreshToken(String token) {
        return authenticatedService.refreshTokenJWT(token);
    }    
}
