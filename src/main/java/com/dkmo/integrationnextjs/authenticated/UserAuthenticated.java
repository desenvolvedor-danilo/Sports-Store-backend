package com.dkmo.integrationnextjs.authenticated;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;

import com.dkmo.integrationnextjs.dto.LoginDto;
import com.dkmo.integrationnextjs.dto.TokenDto;
import com.dkmo.integrationnextjs.services.AuthenticatedUserService;
@Component
public class UserAuthenticated {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private AuthenticatedUserService authenticatedService;

    public TokenDto getScope(LoginDto body){
        var userAutenticationToken = new UsernamePasswordAuthenticationToken(body.email(), body.password());
        authenticationManager.authenticate(userAutenticationToken);
        TokenDto token = authenticatedService.getToken(body);
        return token;
    }
    public String getTokenAcess(LoginDto body){
        var userAutenticationToken = new UsernamePasswordAuthenticationToken(body.email(), body.password());
        authenticationManager.authenticate(userAutenticationToken);
        TokenDto token = authenticatedService.getToken(body);
        return token.token();
    }
}
