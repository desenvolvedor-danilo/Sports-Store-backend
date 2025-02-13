package com.dkmo.integrationnextjs.services;


import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.dkmo.integrationnextjs.dto.ProductDto;
import com.dkmo.integrationnextjs.models.Products;
import com.dkmo.integrationnextjs.repository.ProductsRepository;
import com.dkmo.integrationnextjs.utils.ProductsFilesSave;
@Service
public class ProductInsertService {
    ProductsFilesSave productsFilesSave = new ProductsFilesSave();
    
    @Autowired
    private ProductsRepository productsRepository;

    public ResponseEntity<String> insertProduct(ProductDto product){
        double desconto = 100-product.precoNovo()/product.precoAntigo()*100;
        Products prod = new Products();
        prod.setDesconto(desconto);
        BeanUtils.copyProperties(product, prod);
        double valorParcela =  product.precoNovo()/product.parcelado();
        prod.setValorParcela(valorParcela);
        productsRepository.save(prod);
        return ResponseEntity.ok().body("Created with successfully");
    }
    public String insertProduct(MultipartFile file){
        return productsFilesSave.saveFile(file);
    }
}
