package com.dkmo.integrationnextjs.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Deal {
    @Id
    @GeneratedValue
    private long id;    
    private String titulo;
    private String caminhoFoto;
    private String nome;
    private String valor;     
}
