package com.dkmo.integrationnextjs.interfaces;

import org.springframework.http.ResponseEntity;
import com.dkmo.integrationnextjs.models.Logins;
import com.dkmo.integrationnextjs.models.UserRegister;
public interface IGetInfoAccount{
    public UserRegister getInfoUsers(String email);
    public ResponseEntity<String> getUsers(String email);
    public boolean getVerifiedAccount(Logins email);
    
}
