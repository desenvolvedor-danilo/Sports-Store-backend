package com.dkmo.integrationnextjs.interfaces;

import org.springframework.http.ResponseEntity;

import com.dkmo.integrationnextjs.dto.ProductDto;
import com.dkmo.integrationnextjs.models.Products;

public interface IEditService {
    public ResponseEntity<Products> editProdutoService(ProductDto productDto);
}
