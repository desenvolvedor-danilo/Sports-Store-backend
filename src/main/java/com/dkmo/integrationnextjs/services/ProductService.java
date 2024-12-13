package com.dkmo.integrationnextjs.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.dkmo.integrationnextjs.dto.ProductDto;
import com.dkmo.integrationnextjs.models.Products;
import com.dkmo.integrationnextjs.repository.ProductsRepository;
@Service
public class ProductService {
    @Autowired
    private ProductsRepository productsRepository;

    public ResponseEntity<String> insertProduct(ProductDto product){
        Products prod = new Products();
        prod.setNameProduct(product.name());
        prod.setPrice(product.price());
        prod.setNewPrice(product.newPrice());
        prod.setPathNamePhoto(product.pathName());
        productsRepository.save(prod);
        return ResponseEntity.ok().body("Created with successfully");
    }
}
