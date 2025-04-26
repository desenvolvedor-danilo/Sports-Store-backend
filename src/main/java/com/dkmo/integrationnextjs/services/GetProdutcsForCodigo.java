package com.dkmo.integrationnextjs.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.dkmo.integrationnextjs.interfaces.IGetProductsForCodigo;
import com.dkmo.integrationnextjs.models.Products;
import com.dkmo.integrationnextjs.repository.ProductsRepository;

@Service
public class GetProdutcsForCodigo implements IGetProductsForCodigo {
    @Autowired
    private ProductsRepository productsRepository;
    
    @Override
    public ResponseEntity<Products> getProduct(Long codigo){
        Products product = productsRepository.findByCodigo(codigo);
        if(product!=null){
            return ResponseEntity.ok(product);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
    
}
