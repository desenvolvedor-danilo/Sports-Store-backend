package com.dkmo.integrationnextjs.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.dkmo.integrationnextjs.models.Slides;
import com.dkmo.integrationnextjs.repository.SlidesRepository;
@Service
public class GetSlidesService {
    @Autowired
    private SlidesRepository slidesRepository;

    public ResponseEntity<List<Slides>> findAllSlides(){
       List<Slides> slides = slidesRepository.findAll();
            return ResponseEntity.ok().body(slides);
    
}
}
