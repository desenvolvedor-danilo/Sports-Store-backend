package com.dkmo.integrationnextjs.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
@Data
@Entity
public class Slides {
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private long id;
   private String codigo;
   private String foto;
}
