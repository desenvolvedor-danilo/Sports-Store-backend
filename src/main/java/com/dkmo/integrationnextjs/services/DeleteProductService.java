package com.dkmo.integrationnextjs.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.dkmo.integrationnextjs.interfaces.IDeleteProduct;
import com.dkmo.integrationnextjs.models.Products;
import com.dkmo.integrationnextjs.repository.ProductsRepository;
@Service
public class DeleteProductService implements IDeleteProduct{
@Autowired
private ProductsRepository productsRepository;
    @Override
    public ResponseEntity<String> deleteProduct(long codigo) {
        Products products = productsRepository.findByCodigo(codigo);
        if(products!=null){
            productsRepository.delete(products);
            return ResponseEntity.ok().body("Produto excluído");
        }
        return ResponseEntity.badRequest().body("Erro ao excluir produto. Parece que o produto já não existe mais na base de dados");
    }
    
}
