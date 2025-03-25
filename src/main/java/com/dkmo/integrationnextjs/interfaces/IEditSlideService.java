package com.dkmo.integrationnextjs.interfaces;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface IEditSlideService {
    public ResponseEntity<String> editSlide(String codigo,MultipartFile file);
}
