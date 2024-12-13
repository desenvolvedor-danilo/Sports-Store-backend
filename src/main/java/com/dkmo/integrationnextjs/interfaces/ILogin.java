package com.dkmo.integrationnextjs.interfaces;

import org.springframework.http.ResponseEntity;
import com.dkmo.integrationnextjs.dto.LoginDto;
import com.dkmo.integrationnextjs.dto.TokenDto;
public interface ILogin {
    public ResponseEntity<TokenDto> login(LoginDto body);
}
