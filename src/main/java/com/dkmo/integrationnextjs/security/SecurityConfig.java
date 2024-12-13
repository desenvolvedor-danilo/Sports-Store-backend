package com.dkmo.integrationnextjs.security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.dkmo.integrationnextjs.filters.UserFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private UserFilter filter;
    @Autowired
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception{
        return http
        .csrf(csrf -> csrf.disable())
        .headers(headers -> headers.frameOptions(frameOptions -> frameOptions.sameOrigin()))
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authorizeHttpRequests(authorize -> authorize.requestMatchers(HttpMethod.POST,"/user/register").permitAll()
        .requestMatchers(HttpMethod.POST,"/user/login").permitAll()
        .requestMatchers(HttpMethod.GET,"/user/confirm").permitAll()
        .requestMatchers(HttpMethod.GET,"/user/redifine").permitAll()
        .requestMatchers(HttpMethod.GET,"/user/email").permitAll()
        .requestMatchers(HttpMethod.PUT,"/user/reset").permitAll()
        .requestMatchers("/h2-console/**").permitAll()
        .requestMatchers(HttpMethod.GET,"/user/verify").permitAll()
        .requestMatchers(HttpMethod.GET,"/user/get-token").permitAll()
        .requestMatchers(HttpMethod.POST,"/user/refresh-token").permitAll()
        .requestMatchers(HttpMethod.POST,"/admin/register").permitAll()
        .requestMatchers(HttpMethod.POST,"/admin/login").permitAll()
        .anyRequest()
        .authenticated())
        .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
        .build();
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticate)throws Exception{
        return authenticate.getAuthenticationManager();
    }
}
