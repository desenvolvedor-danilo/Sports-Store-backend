package com.dkmo.integrationnextjs.interfaces;

import org.springframework.http.ResponseEntity;

public interface IVerifyCodeService {
    public ResponseEntity<String> verifyCode(String code);
}
