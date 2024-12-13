package com.dkmo.integrationnextjs.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.dkmo.integrationnextjs.interfaces.IVerifyCodeService;
import com.dkmo.integrationnextjs.models.Logins;
import com.dkmo.integrationnextjs.repository.LoginsRepository;

@Service
public class VerifiedCodeService implements IVerifyCodeService{
@Autowired
private LoginsRepository loginsRepository;

@Override
public ResponseEntity<String> verifyCode(String code) {
    Logins userLogin = loginsRepository.findByCode(code);
        if (userLogin != null) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("código correto");
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }
}
