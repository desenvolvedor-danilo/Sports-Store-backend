package com.dkmo.integrationnextjs.utils;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.web.multipart.MultipartFile;

import com.dkmo.integrationnextjs.interfaces.IHandlerFiles;

public class SlidesFilesSave implements IHandlerFiles {

    @Override
    public String saveFile(MultipartFile file) {
        var path = "src/main/resources/static/files/slides/";
        try {
            if(!file.isEmpty()){
            byte [] imagem = file.getBytes();
            Path fullPath = Paths.get(path+file.getOriginalFilename());
            Files.write(fullPath, imagem);                                                                                                                                                      
            return "http://localhost:8080/files/slides/"+file.getOriginalFilename();
            }
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
            }
            return null;
    }
}
