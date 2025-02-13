package com.dkmo.integrationnextjs.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class CorsConfigurationAdmin implements WebMvcConfigurer{
@Override
public void addCorsMappings(@NonNull CorsRegistry registry){
    registry.addMapping("/admin/**")
    .allowedOrigins("http://localhost:3000")
    .allowedHeaders("*")
    .allowedMethods("GET","POST","PUT","DELETE");
}

    
}
