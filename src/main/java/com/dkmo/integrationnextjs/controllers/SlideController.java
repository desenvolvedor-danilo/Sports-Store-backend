package com.dkmo.integrationnextjs.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.dkmo.integrationnextjs.models.Slides;
import com.dkmo.integrationnextjs.services.EditSlideService;
import com.dkmo.integrationnextjs.services.GetSlidesService;
import com.dkmo.integrationnextjs.services.SlideService;

@RestController 
@RequestMapping("/admin")    
public class SlideController {
    @Autowired
    private SlideService slideService;
    @Autowired
    private GetSlidesService getSlidesService;

    @Autowired
    private EditSlideService editSlideService;
    
    @PostMapping("/slide-info")
    public ResponseEntity<String> slideInsert(@RequestParam(name = "slide") String codigo,@RequestParam(name="file")MultipartFile file){
        return slideService.insertSlide(codigo,file);
    }        
    @GetMapping("/list-slides")
    public ResponseEntity<List<Slides>> findAllSlides() {
        return getSlidesService.findAllSlides();
    }
    @PutMapping("/edit-slides")
    public ResponseEntity<String> editSlides(@RequestParam(name="codigo")String codigo, @RequestParam(name = "file") MultipartFile file) {
        return editSlideService.editSlide(codigo, file);
    }
}
