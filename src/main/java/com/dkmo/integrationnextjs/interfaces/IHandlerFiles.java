package com.dkmo.integrationnextjs.interfaces;


import org.springframework.web.multipart.MultipartFile;

public interface IHandlerFiles {
    public String saveFile(MultipartFile file);
}
