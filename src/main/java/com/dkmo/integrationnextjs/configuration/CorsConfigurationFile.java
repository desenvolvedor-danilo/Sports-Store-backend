package com.dkmo.integrationnextjs.configuration;

import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class CorsConfigurationFile implements WebMvcConfigurer {
    @Override
public void addCorsMappings(@NonNull CorsRegistry registry){
    registry.addMapping("/files/images/**")
    .allowedOrigins("http://localhost:3000")
    .allowedHeaders("*")
    .allowedMethods("GET","POST","PUT","DELETE");
}
}
