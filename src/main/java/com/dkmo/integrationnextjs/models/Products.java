package com.dkmo.integrationnextjs.models;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Products {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private long id;
private String pathNamePhoto;
private String nameProduct;
private int price;
private int newPrice;
private int[] rangeLenght;
    
}
