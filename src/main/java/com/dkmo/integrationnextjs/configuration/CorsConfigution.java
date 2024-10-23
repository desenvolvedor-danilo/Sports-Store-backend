package com.dkmo.integrationnextjs.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfigution implements WebMvcConfigurer{

@Override
public void addCorsMappings(@NonNull CorsRegistry registry){
    registry.addMapping("/**")
    .allowedOrigins("http://localhost:3000")
    .allowedHeaders("*")
    .allowedMethods("GET","POST","PUT","DELETE");
}
}