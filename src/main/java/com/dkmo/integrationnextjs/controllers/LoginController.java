package com.dkmo.integrationnextjs.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.dkmo.integrationnextjs.dto.LoginDto;
import com.dkmo.integrationnextjs.dto.RequestRegisterDto;
import com.dkmo.integrationnextjs.dto.ResponseConfirmedDto;
import com.dkmo.integrationnextjs.dto.ResponseDto;
import com.dkmo.integrationnextjs.services.UserService;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/user")
public class LoginController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto body){
        return userService.login(body);
    }
    @PostMapping("/register")
    public ResponseEntity<ResponseDto> register(@RequestBody RequestRegisterDto body){
       return userService.register(body);
    }
    @GetMapping("/confirm")
    public ResponseEntity<ResponseConfirmedDto> confirmEmail(@RequestParam(name = "code") String code){
        return userService.confirmEmail(code);
    }
    @GetMapping("/redifine")
    public String redifinePassword(@RequestParam(name="email") String body){
        return userService.redifinePassword(body);
    }
    @PutMapping("/reset")
    public ResponseEntity<String> reset(@RequestParam(name="email")String email, @RequestBody LoginDto password){
        return userService.resetPassword(email,password);
    }
    @GetMapping("/email")
    public ResponseEntity<String> verifyCode(@RequestParam(name="code") String code){
        return userService.verifyCode(code);
    }    
    @GetMapping ("/get-users")
    public ResponseEntity<String> getUsers(@RequestParam(name = "email") String email){
        return userService.getUsers(email);
    }
}