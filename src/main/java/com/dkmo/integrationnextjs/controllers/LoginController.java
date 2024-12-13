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
import com.dkmo.integrationnextjs.dto.TokenDto;
import com.dkmo.integrationnextjs.models.UserRegister;
import com.dkmo.integrationnextjs.services.EditInfoService;
import com.dkmo.integrationnextjs.services.GetInfoAccountService;
import com.dkmo.integrationnextjs.services.RedifinePasswordService;
import com.dkmo.integrationnextjs.services.RefreshTokenService;
import com.dkmo.integrationnextjs.services.RegisterService;
import com.dkmo.integrationnextjs.services.LoginService;
import com.dkmo.integrationnextjs.services.VerifiedCodeService;
import com.dkmo.integrationnextjs.services.VerifiedEmailService;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/user")
public class LoginController {
    @Autowired
    private RegisterService registerService;
    @Autowired
    private LoginService loginService;
    @Autowired
    private RefreshTokenService refreshTokenService;
    @Autowired
    private EditInfoService editInfoService;
    @Autowired
    private RedifinePasswordService redifinePasswordService;
    @Autowired 
    private GetInfoAccountService infoAccountService;
    @Autowired
    private VerifiedEmailService verifiedEmailService;
    @Autowired
    private VerifiedCodeService verifyCodeService;
    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody LoginDto body){
        return loginService.login(body);
    }
    @PostMapping("/register")
    public ResponseEntity<ResponseDto> register(@RequestBody RequestRegisterDto body){
       return registerService.register(body);
    }
    @GetMapping("/confirm")
    public ResponseEntity<ResponseConfirmedDto> confirmEmail(@RequestParam(name = "code") String code){
        return verifiedEmailService.confirmEmail(code);
    }
    @GetMapping("/redifine")
    public String redifinePassword(@RequestParam(name="email") String body){
        return redifinePasswordService.redifinePassword(body);
    }
    @PutMapping("/reset")
    public ResponseEntity<String> reset(@RequestParam(name="email")String email, @RequestBody LoginDto password){
        return redifinePasswordService.resetPassword(email,password);
    }
    @GetMapping("/email")
    public ResponseEntity<String> verifyCode(@RequestParam(name="code") String code){
        return verifyCodeService.verifyCode(code);
    }    
    @GetMapping ("/get-users")
    public ResponseEntity<String> getUsers(@RequestParam(name = "email") String email){
        return infoAccountService.getUsers(email);
    }
    @GetMapping("userinfo")
    public UserRegister getInfoUsers(@RequestParam(name = "email")String email){
        return infoAccountService.getInfoUsers(email);
    }
    @PutMapping("edit-info")
    public ResponseEntity<String> editInfo(@RequestBody RequestRegisterDto user){
        return editInfoService.editInfo(user);
    }
    @PutMapping("edit-address")
    public ResponseEntity<String> editAddress(@RequestBody RequestRegisterDto userAddress){
        return editInfoService.editAddress(userAddress);
    }
    @PostMapping("refresh-token")
    public TokenDto refreshToken(@RequestParam(name = "token") String refresh){
        return refreshTokenService.refreshToken(refresh);
    }

}