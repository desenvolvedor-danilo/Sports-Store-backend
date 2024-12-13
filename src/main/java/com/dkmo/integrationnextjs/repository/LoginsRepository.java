package com.dkmo.integrationnextjs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.dkmo.integrationnextjs.models.Logins;

public interface LoginsRepository extends JpaRepository<Logins,Long>{
    public Logins findByEmail(String email);
    public Logins findByCode(String code);
    public Logins findByUsuario(String username);
}
