package com.dkmo.integrationnextjs.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.dkmo.integrationnextjs.interfaces.IEditSlideService;
import com.dkmo.integrationnextjs.models.Slides;
import com.dkmo.integrationnextjs.repository.SlidesRepository;
import com.dkmo.integrationnextjs.utils.SlidesFilesSave;


@Service
public class EditSlideService implements IEditSlideService {
    @Autowired
    private SlidesRepository slideRepository;
    SlidesFilesSave slideFilesSave = new SlidesFilesSave();

    @Override
    public ResponseEntity<String> editSlide(String codigo, MultipartFile file) {
        Slides slide = slideRepository.findByCodigo(codigo);
        if (slide != null) {
            slide.setFoto(slideFilesSave.saveFile(file));
            slideRepository.save(slide);
            return ResponseEntity.ok().body("Editado com sucesso");
        } else {
            return ResponseEntity.badRequest().body("Houve um erro");
        }

    }
}