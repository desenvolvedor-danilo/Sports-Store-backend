package com.dkmo.integrationnextjs.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.dkmo.integrationnextjs.models.Slides;
import com.dkmo.integrationnextjs.repository.SlidesRepository;
import com.dkmo.integrationnextjs.utils.SlidesFilesSave;


@Service
public class SlideService {
    SlidesFilesSave slidesFilesSave = new SlidesFilesSave();
    @Autowired
    private SlidesRepository slidesRepository;

    public ResponseEntity<String> insertSlide(String codigo, MultipartFile file) {
        Slides slide = new Slides();
        slide.setFoto(slidesFilesSave.saveFile(file));
        slide.setCodigo(codigo);
        slidesRepository.save(slide);
        return ResponseEntity.ok().body("created successfully");
    }
}
