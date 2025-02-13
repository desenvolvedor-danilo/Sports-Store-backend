package com.dkmo.integrationnextjs.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dkmo.integrationnextjs.interfaces.IGetProductsForCodigo;
import com.dkmo.integrationnextjs.models.Products;
import com.dkmo.integrationnextjs.repository.ProductsRepository;

@Service
public class GetProdutcsForCodigo implements IGetProductsForCodigo {
    @Autowired
    private ProductsRepository productsRepository;
    
    @Override
    public Products getProduct(Long codigo){
        Products product = productsRepository.findByCodigo(codigo);
        if(product!=null){
            return product;
        }
        return null;
    }
    
}
