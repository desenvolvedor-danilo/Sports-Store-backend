package com.dkmo.integrationnextjs.interfaces;

import org.springframework.http.ResponseEntity;
import com.dkmo.integrationnextjs.dto.LoginDto;
import com.dkmo.integrationnextjs.dto.RequestRegisterDto;
import com.dkmo.integrationnextjs.dto.ResponseConfirmedDto;
import com.dkmo.integrationnextjs.dto.ResponseDto;
import com.dkmo.integrationnextjs.models.Logins;
public interface UserInterfaceService {
    public ResponseEntity<ResponseDto> register(RequestRegisterDto body);
    public ResponseEntity<String> login(LoginDto body);
    public boolean validateCpf(String cpf);
    public String sendEmail(String to);
    public ResponseEntity<ResponseConfirmedDto> confirmEmail(String code);
    public String redifinePassword(String body);
    public ResponseEntity<String> resetPassword(String email,LoginDto password);
    public void sendLink(String to,String msg);
    public ResponseEntity<String> getUsers(String email);
    public ResponseEntity<String> verifyCode(String code);
    public boolean getVerifiedAccount(Logins email);
}
