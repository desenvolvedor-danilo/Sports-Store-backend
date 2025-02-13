package com.dkmo.integrationnextjs.services;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.dkmo.integrationnextjs.dto.LoginDto;
import com.dkmo.integrationnextjs.dto.TokenDto;
import com.dkmo.integrationnextjs.models.Logins;
import com.dkmo.integrationnextjs.repository.LoginsRepository;
@Service 
public class AuthenticatedUserService implements UserDetailsService{
   @Autowired
    private LoginsRepository repository;

    
    @Value("key.auth.token")
    private String key;    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByEmail(username);
    }
    public TokenDto getToken(LoginDto loginDto){
        Logins user = repository.findByEmail(loginDto.email());
        return TokenDto.
                builder()
                .token(setToken(user, 72))
                .refreshToken(setToken(user, 1))
                .build();                
            }
            
       public String setToken(Logins user,Integer expirationAt){
        try {
            Algorithm algorithm = Algorithm.HMAC256(key);
            String token = JWT.create()
            .withIssuer("integration-nextjs")
            .withSubject(user.getEmail())
            .withExpiresAt(getDateExpirated(expirationAt))
            .sign(algorithm);
            return token;

        } catch (JWTCreationException e) {
            throw new RuntimeException("Error to the create token "+e.getMessage());
        }
    }
    
    private Instant getDateExpirated(Integer getDateExpirated){
        return LocalDateTime.now().plusHours(getDateExpirated).toInstant(ZoneOffset.of("-03:00"));
    }
    public String validateToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(key);
            return JWT.require(algorithm).withIssuer("integration-nextjs")
            .build()
            .verify(token)
            .getSubject();
        } catch (JWTVerificationException e) {
            return "";
        }
    }
    public TokenDto refreshTokenJWT(String refresh){
        String login = validateToken(refresh);
        Logins user = repository.findByEmail(login);
        var authentication = new UsernamePasswordAuthenticationToken(user,null,user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return TokenDto.
        builder()
        .token(setToken(user, 1))
        .refreshToken(setToken(user, 72))
        .build();
    }
    
}
