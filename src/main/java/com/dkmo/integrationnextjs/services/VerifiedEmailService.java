package com.dkmo.integrationnextjs.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.dkmo.integrationnextjs.dto.ResponseConfirmedDto;
import com.dkmo.integrationnextjs.interfaces.IVerifiedEmail;
import com.dkmo.integrationnextjs.models.Logins;
import com.dkmo.integrationnextjs.repository.LoginsRepository;
@Service
public class VerifiedEmailService implements IVerifiedEmail{

    @Autowired
    private LoginsRepository loginsRepository;
    @Override
    public ResponseEntity<ResponseConfirmedDto> confirmEmail(String code) {
        Logins userCode = loginsRepository.findByCode(code);
        if (userCode != null) {
            userCode.setVerifiedAccount(true);
            loginsRepository.save(userCode);
            return ResponseEntity.ok().body(new ResponseConfirmedDto("Account confirmed"));
        }
        return ResponseEntity.badRequest().body(new ResponseConfirmedDto("Code is not found"));
    }
    
}
