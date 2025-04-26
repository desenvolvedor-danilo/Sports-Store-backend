package com.dkmo.integrationnextjs.services;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.dkmo.integrationnextjs.dto.ProductDto;
import com.dkmo.integrationnextjs.interfaces.IEditService;
import com.dkmo.integrationnextjs.models.Products;
import com.dkmo.integrationnextjs.repository.ProductsRepository;

@Service
public class EditProductService implements IEditService{
    @Autowired
    private ProductsRepository productsRepository;

    @Override
    public ResponseEntity<Products> editProdutoService(ProductDto productDto) {
        Products products = productsRepository.findByCodigo(productDto.codigo());
        if (products != null) {
            double desconto = 100-productDto.precoNovo()/productDto.precoAntigo()*100;
            double valorParcela =  productDto.precoNovo()/productDto.parcelado();
            products.setDesconto(desconto);
            products.setValorParcela(valorParcela);
            BeanUtils.copyProperties(productDto, products);
            productsRepository.save(products);
            return ResponseEntity.ok().body(products);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

}
