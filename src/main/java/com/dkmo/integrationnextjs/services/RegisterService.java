package com.dkmo.integrationnextjs.services;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
// import com.dkmo.integrationnextjs.EmailService.SenderEmail;
import com.dkmo.integrationnextjs.EmailService.SenderEmailMimeMessage;
import com.dkmo.integrationnextjs.dto.RequestRegisterDto;
import com.dkmo.integrationnextjs.dto.ResponseDto;
import com.dkmo.integrationnextjs.enums.Roles;
import com.dkmo.integrationnextjs.interfaces.IRegister;
import com.dkmo.integrationnextjs.models.Logins;
import com.dkmo.integrationnextjs.models.UserRegister;
import com.dkmo.integrationnextjs.repository.LoginsRepository;
import com.dkmo.integrationnextjs.repository.RegisterRepository;
import com.dkmo.integrationnextjs.validations.ValidationCpf;

@Service
public class RegisterService
        implements IRegister {
    @Autowired
    private ValidationCpf validationCpf;
    @Autowired
    private RegisterRepository registerRepository;
    @Autowired
    private LoginsRepository loginsRepository;
    @Autowired
    private PasswordEncoder encoder;
    // private SenderEmail send;
    SenderEmailMimeMessage sender = new SenderEmailMimeMessage();

    @Override
    public ResponseEntity<ResponseDto> register(RequestRegisterDto body) {
        UserRegister user = registerRepository.findByEmail(body.email());
        if (user == null) {
            Logins logins = new Logins();
            UserRegister newUser = new UserRegister();
            BeanUtils.copyProperties(body, newUser);
            newUser.setPassword(encoder.encode(body.password()));
            if (!validationCpf.validateCpf(newUser.getCpf())) {
                return ResponseEntity.status(412).build();
            }
            registerRepository.save(newUser);
            logins.setSenha(newUser.getPassword());
            logins.setCode(sender.sendEmail(body.email()));
            logins.setRole(Roles.USER);
            logins.setVerifiedAccount(false);
            logins.setUsuario(newUser.getUsername());
            BeanUtils.copyProperties(newUser,logins);
            loginsRepository.save(logins);
            return ResponseEntity.ok()
                    .build();
        }
        return ResponseEntity.badRequest().build();
    }
}