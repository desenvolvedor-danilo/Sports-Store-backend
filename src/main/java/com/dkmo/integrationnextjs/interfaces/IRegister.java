package com.dkmo.integrationnextjs.interfaces;

import org.springframework.http.ResponseEntity;

import com.dkmo.integrationnextjs.dto.RequestRegisterDto;
import com.dkmo.integrationnextjs.dto.ResponseDto;

public interface IRegister {
    public ResponseEntity<ResponseDto> register(RequestRegisterDto body);
    
}
