package com.dkmo.integrationnextjs.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dkmo.integrationnextjs.dto.LoginDto;
import com.dkmo.integrationnextjs.dto.RequestRegisterDto;
import com.dkmo.integrationnextjs.dto.ResponseDto;
import com.dkmo.integrationnextjs.dto.TokenDto;
import com.dkmo.integrationnextjs.services.AdminService;

@RestController
@RequestMapping("/admin")
public class AdminController{
    @Autowired
    private AdminService adminService;
    @PostMapping("/register")
    public ResponseEntity<ResponseDto> registerAdmin(@RequestBody RequestRegisterDto registerDto){
        return adminService.register(registerDto);
    }
    @PostMapping("/login")
   public ResponseEntity<TokenDto> loginAdmin(@RequestBody LoginDto login){
    return adminService.login(login);
   }
}
