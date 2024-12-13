package com.dkmo.integrationnextjs.interfaces;

import org.springframework.http.ResponseEntity;
import com.dkmo.integrationnextjs.dto.LoginDto;
public interface IRedifinePassword {
    public String redifinePassword(String body);
    public ResponseEntity<String> resetPassword(String email,LoginDto password);
    
}