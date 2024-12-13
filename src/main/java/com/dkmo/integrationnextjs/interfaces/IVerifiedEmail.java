package com.dkmo.integrationnextjs.interfaces;

import org.springframework.http.ResponseEntity;

import com.dkmo.integrationnextjs.dto.ResponseConfirmedDto;

public interface IVerifiedEmail {
    public ResponseEntity<ResponseConfirmedDto> confirmEmail(String code);
}
