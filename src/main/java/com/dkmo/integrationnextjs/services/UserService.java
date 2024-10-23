package com.dkmo.integrationnextjs.services;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.dkmo.integrationnextjs.dto.LoginDto;
import com.dkmo.integrationnextjs.dto.RequestRegisterDto;
import com.dkmo.integrationnextjs.dto.ResponseConfirmedDto;
import com.dkmo.integrationnextjs.dto.ResponseDto;
import com.dkmo.integrationnextjs.enums.Roles;
import com.dkmo.integrationnextjs.interfaces.UserInterfaceService;
import com.dkmo.integrationnextjs.models.Logins;
import com.dkmo.integrationnextjs.models.UserRegister;
import com.dkmo.integrationnextjs.repository.LoginsRepository;
import com.dkmo.integrationnextjs.repository.RegisterRepository;

import br.com.caelum.stella.validation.CPFValidator;

@Service
public class UserService implements UserInterfaceService{
    private final JavaMailSender sender;
    
    public UserService(JavaMailSender sender){
        this.sender = sender;
    }
    
@Autowired
    private RegisterRepository registerRepository;
    @Autowired
    private LoginsRepository loginsRepository;
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private AuthenticatedService authenticatedService;
    @Override
    public ResponseEntity<ResponseDto> register(RequestRegisterDto body) {
        UserRegister user =  registerRepository.findByEmail(body.email());        
        if(user==null){
            UserRegister newUser = new UserRegister();
            newUser.setPasswor(encoder.encode(body.password()));
            newUser.setEmail(body.email());
            newUser.setName(body.name());
            newUser.setCpf(body.cpf());
             if(!validateCpf(newUser.getCpf())){
                return ResponseEntity.status(412).build();
            
             }
            newUser.setUserna(body.username());
            newUser.setCep(body.cep());
            newUser.setLogradouro(body.logradouro());
            newUser.setBairro(body.bairro());
            newUser.setLocalidade(body.localidade());
            newUser.setUf(body.uf());
            String code = sendEmail(body.email());
            registerRepository.save(newUser);
            Logins logins = new Logins();
            logins.setEmail(newUser.getEmail());
            logins.setName(newUser.getName());
            logins.setPass(newUser.getPasswor());
            logins.setCode(code);
            logins.setRole(Roles.USER);
            logins.setUserna(newUser.getUserna());
            logins.setVerifiedAccount(false);
            loginsRepository.save(logins);
            return ResponseEntity.ok().body(new ResponseDto(newUser.getId(),newUser.getEmail(),newUser.getName(),newUser.getUserna(),newUser.getPasswor()));
       }
        return ResponseEntity.badRequest().build();
    }

    @Override
    public ResponseEntity<String> login(LoginDto body) {
        Logins user =  loginsRepository.findByEmail(body.email());
        boolean isVerified = getVerifiedAccount(user);
        if(user!=null /*&& isVerified */ ){
            var userAutenticationToken = new UsernamePasswordAuthenticationToken(body.email(),body.password()); 
            authenticationManager.authenticate(userAutenticationToken);
           String token = authenticatedService.getToken(body);
           user.setToken(token);
           loginsRepository.save(user);
           return ResponseEntity.ok().body(token);
        }
        return ResponseEntity.badRequest().build();
    }   
    @Override
    public boolean getVerifiedAccount(Logins email){
        Logins user = loginsRepository.findByEmail(email.getEmail());
        if(user!=null){
            
            if(user.isVerifiedAccount()){
                return 
                true;
            }
            return false;
        }
        return false;
    }
    @Override
    public boolean validateCpf(String cpf) {
        CPFValidator cpfValidator = new CPFValidator();
        try {
         cpfValidator.assertValid(cpf);
         return true; 
        } catch (Exception e) {
         e.printStackTrace();
         return false;
        }
     }
     @SuppressWarnings("null")
     @Override
     public String sendEmail(String to){
        var message = new SimpleMailMessage();
        Random random = new Random();
        message.setTo(to);
        message.setText("CÓDIGO DE \nSEGURANÇA \n\nUse o código de segurança para confirmar sua conta \n\nclique no link abaixo \n\nhttp://localhost:3000/confirm \n\ncódigo de segurança: "+random.nextInt(1000,9999));
        sender.send(message);
        return message.getText().replace("CÓDIGO DE \nSEGURANÇA \n\nUse o código de segurança para confirmar sua conta \n\nclique no link abaixo \n\nhttp://localhost:3000/confirm \n\ncódigo de segurança:", "").trim();
        }
        @Override
        public void sendLink(String to,String msg){
            var message = new SimpleMailMessage();
            message.setTo(to);
            message.setText(msg);
            sender.send(message);
        }
        @Override
        public ResponseEntity<ResponseConfirmedDto> confirmEmail(String code){
            Logins userCode = loginsRepository.findByCode(code);
            if(userCode!=null){
                userCode.setVerifiedAccount(true);
                loginsRepository.save(userCode);
                return  ResponseEntity.ok().body(new ResponseConfirmedDto("Account confirmed"));
            }
            return ResponseEntity.badRequest().body(new ResponseConfirmedDto("Code is not found"));
        }
        @Override
        public ResponseEntity<String> getUsers(String email){
            Logins user = loginsRepository.findByEmail(email);
            if(user!=null){
                return ResponseEntity.ok().body(user.getUserna());
            }
            return ResponseEntity.notFound().build();
        }

        @Override
        public String redifinePassword(String body) {
        Logins user = loginsRepository.findByEmail(body);
        if(user!=null){
        String code = sendEmail(body);
        loginsRepository.save(user);
        return code;
    }
    return null;
}
@Override
public ResponseEntity<String> verifyCode(String code){
Logins userLogin = loginsRepository.findByCode(code);
    if(userLogin!=null){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("código correto");
    }
    return ResponseEntity.status(HttpStatus.CONFLICT).build();
}
@Override
public ResponseEntity<String> resetPassword(String email,LoginDto password){
    UserRegister user = registerRepository.findByEmail(email);
    if(user!=null){
        user.setPasswor(password.password());
        return ResponseEntity.ok().body(user.getPasswor());
    }
    return ResponseEntity.badRequest().build();
    

}

}

