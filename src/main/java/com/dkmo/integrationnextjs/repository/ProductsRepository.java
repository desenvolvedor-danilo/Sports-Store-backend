package com.dkmo.integrationnextjs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dkmo.integrationnextjs.models.Products;

public interface ProductsRepository extends JpaRepository<Products,Long> {
    
}
