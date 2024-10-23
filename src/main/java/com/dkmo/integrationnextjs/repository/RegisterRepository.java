package com.dkmo.integrationnextjs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dkmo.integrationnextjs.models.UserRegister;


public interface RegisterRepository extends JpaRepository<UserRegister,Long>{
    UserRegister findByEmail(String user);
    UserRegister findByUserna(String username);
}
