package com.dkmo.integrationnextjs.services;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.dkmo.integrationnextjs.dto.LoginDto;
import com.dkmo.integrationnextjs.models.Logins;
import com.dkmo.integrationnextjs.repository.LoginsRepository;
@Service 
public class AuthenticatedService implements UserDetailsService{
   @Autowired
    private LoginsRepository repository;
    
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByEmail(username);
    }
    public String getToken(LoginDto loginDto){
        Logins user = repository.findByEmail(loginDto.email());
        return setToken(user);
    }
       public String setToken(Logins user){
        try {
            Algorithm algorithm = Algorithm.HMAC256("my-secret");
            String token = JWT.create()
            .withIssuer("integration-nextjs")
            .withSubject(user.getEmail())
            .withExpiresAt(getDateExpirated())
            .sign(algorithm);
            return token;

        } catch (JWTCreationException e) {
            throw new RuntimeException("Error to the create token "+e.getMessage());
        }
    }
    private Instant getDateExpirated(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
    public String validateToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256("my-secret");
            return JWT.require(algorithm).withIssuer("integration-nextjs")
            .build()
            .verify(token)
            .getSubject();
        } catch (JWTVerificationException e) {
            return "";
        }
    }
    
}
