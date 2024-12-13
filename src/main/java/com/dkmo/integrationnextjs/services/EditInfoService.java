package com.dkmo.integrationnextjs.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.dkmo.integrationnextjs.dto.RequestRegisterDto;
import com.dkmo.integrationnextjs.interfaces.IEditInfo;
import com.dkmo.integrationnextjs.models.Logins;
import com.dkmo.integrationnextjs.models.UserRegister;
import com.dkmo.integrationnextjs.repository.LoginsRepository;
import com.dkmo.integrationnextjs.repository.RegisterRepository;

@Service
public class EditInfoService implements IEditInfo {
@Autowired
private RegisterRepository registerRepository;
@Autowired
private LoginsRepository loginsRepository;
    @Override
    public ResponseEntity<String> editInfo(RequestRegisterDto user) {
        UserRegister userRegistered = registerRepository.findByEmail(user.email());
        Logins userLogins = loginsRepository.findByEmail(user.email());
        userRegistered.setName(user.name());
        userRegistered.setUsername(user.username());
        userLogins.setName(user.name());
        userLogins.setUsuario(user.username());
        loginsRepository.save(userLogins);
        registerRepository.save(userRegistered);
        return ResponseEntity.ok().body("Alterado com sucesso");
    }

    @Override
    public ResponseEntity<String> editAddress(RequestRegisterDto userAddress) {
        UserRegister user = registerRepository.findByEmail(userAddress.email());
        if (user != null) {
            user.setLocalidade(userAddress.localidade());
            user.setLogradouro(userAddress.logradouro());
            user.setCep(userAddress.cep());
            user.setBairro(userAddress.bairro());
            user.setUf(userAddress.uf());
            user.setComplemento(userAddress.complemento());
            registerRepository.save(user);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();

    }
    
}
