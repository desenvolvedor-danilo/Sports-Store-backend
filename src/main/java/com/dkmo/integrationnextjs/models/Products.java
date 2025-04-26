package com.dkmo.integrationnextjs.models;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Products {

@GeneratedValue(strategy = GenerationType.IDENTITY)
@Id
private Long id;
@Column(nullable = false,unique = true,length = 10)
private Long codigo;
private String categoria;
private String nomeImagem;
private String nome;
private String edicao;
private double precoAntigo;
private double precoNovo;
private double desconto;
private double parcelado;
private double valorParcela;
}


  