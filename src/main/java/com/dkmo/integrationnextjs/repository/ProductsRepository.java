package com.dkmo.integrationnextjs.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;



import com.dkmo.integrationnextjs.models.Products;

public interface ProductsRepository extends JpaRepository<Products,Long> {
    
    public Products findByCodigo(Long codigo);
    public List<Products> findByCategoria(String categoria);
    public List<Products> findByNomeContaining(String key);
    
}
