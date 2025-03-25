package com.dkmo.integrationnextjs.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.dkmo.integrationnextjs.models.Products;
import com.dkmo.integrationnextjs.repository.ProductsRepository;

@Service
public class GetProductService {
    @Autowired
    private ProductsRepository productsRepository;
    
    public ResponseEntity<List<Products>>getProducts(){
        List<Products> product = productsRepository.findAll();
        return ResponseEntity.ok().body(product);
        
    }
    public ResponseEntity<Products> getProductsById(Long id){
        Optional<Products> product = productsRepository.findById(id);
        if (product.isPresent()) {
            return ResponseEntity.ok().body(product.get());
        }
        return ResponseEntity.badRequest().build();
    }
    public ResponseEntity<List<Products>> getProductsForCategory(String categoria){
        List<Products> products = productsRepository.findByCategoria(categoria);
        return ResponseEntity.ok().body(products);
    }
    public ResponseEntity<List<Products>> getProductsForKeyWord(String key){
        List<Products> products = productsRepository.findByNomeContaining(key);
        return ResponseEntity.ok().body(products);
    }
}
