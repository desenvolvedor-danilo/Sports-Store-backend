package com.dkmo.integrationnextjs.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
// import com.dkmo.integrationnextjs.EmailService.SenderEmail;
import com.dkmo.integrationnextjs.authenticated.UserAuthenticated;
import com.dkmo.integrationnextjs.dto.LoginDto;
import com.dkmo.integrationnextjs.dto.TokenDto;
import com.dkmo.integrationnextjs.interfaces.ILogin;
import com.dkmo.integrationnextjs.models.Logins;
import com.dkmo.integrationnextjs.repository.LoginsRepository;


@Service
public class LoginService
        implements ILogin {
    @Autowired
    private LoginsRepository loginsRepository;
    @Autowired
    private UserAuthenticated auth;
    @Override
    public ResponseEntity<TokenDto> login(LoginDto body) {
        Logins user = loginsRepository.findByEmail(body.email());
        if (user == null) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok().body(auth.getScope(body));
    }
}
