package com.dkmo.integrationnextjs.dto;

import com.dkmo.integrationnextjs.enums.Roles;

public record RequestRegisterDto(String email,String name, String username,String password, String cpf,String cep, String logradouro,String bairro, String localidade,String uf, String complemento, Roles role,String code) {
    
}
