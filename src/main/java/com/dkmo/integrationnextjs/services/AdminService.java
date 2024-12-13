package com.dkmo.integrationnextjs.services;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.dkmo.integrationnextjs.EmailService.SenderEmail;
import com.dkmo.integrationnextjs.dto.LoginDto;
import com.dkmo.integrationnextjs.dto.RequestRegisterDto;
import com.dkmo.integrationnextjs.dto.ResponseDto;
import com.dkmo.integrationnextjs.dto.TokenDto;
import com.dkmo.integrationnextjs.enums.Roles;
import com.dkmo.integrationnextjs.interfaces.ILogin;
import com.dkmo.integrationnextjs.interfaces.IRegister;
import com.dkmo.integrationnextjs.models.Logins;
import com.dkmo.integrationnextjs.models.UserRegister;
import com.dkmo.integrationnextjs.repository.LoginsRepository;
import com.dkmo.integrationnextjs.repository.RegisterRepository;
import com.dkmo.integrationnextjs.validations.ValidationCpf;

@Service
public class AdminService implements ILogin,IRegister {
    @Autowired
    private SenderEmail sender;
    ValidationCpf validation = new ValidationCpf();
    @Autowired
    private RegisterRepository registerAdminRepository;
    @Autowired
    private LoginsRepository adminRepository; 
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private AuthenticatedUserService authenticatedService;
        

    @Override
    public ResponseEntity<ResponseDto> register(RequestRegisterDto body) {
        UserRegister userVerified = registerAdminRepository.findByEmail(body.email());
        if(userVerified==null){
        UserRegister user = new UserRegister();
        user.setPassword(encoder.encode(body.password()));
        user.setUsername(body.username());
        BeanUtils.copyProperties(body, user);
        if(!validation.validateCpf(user.getCpf())){
            return ResponseEntity.status(412).build();
        }
        Logins userAdmin = new Logins();
        Roles role = Roles.ADMIN;
        userAdmin.setUserRegister(user);
        user.setLogins(userAdmin);
        userAdmin.setRole(role);
        adminRepository.save(userAdmin);
        registerAdminRepository.save(user);
        
        
        sender.sendEmail(user.getEmail());
    }
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<TokenDto> login(LoginDto body) {
        Logins user =  adminRepository.findByEmail(body.email());
        // boolean isVerified = getVerifiedAccount(user);
        if(user!=null /*&& isVerified */ ){
            var userAutenticationToken = new UsernamePasswordAuthenticationToken(body.email(),body.password()); 
            authenticationManager.authenticate(userAutenticationToken);
           TokenDto token = authenticatedService.getToken(body);
           user.setToken(token.token());
           adminRepository.save(user);
           return ResponseEntity.ok().body(new TokenDto(token.token(), token.refreshToken()));
        }
        return ResponseEntity.badRequest().build();
    }
   
}
