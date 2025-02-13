package com.dkmo.integrationnextjs.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dkmo.integrationnextjs.dto.ProductDto;
import com.dkmo.integrationnextjs.models.Products;
import com.dkmo.integrationnextjs.services.GetProductService;
import com.dkmo.integrationnextjs.services.GetProdutcsForCodigo;
import com.dkmo.integrationnextjs.services.ProductInsertService;

@RestController
@RequestMapping(value = "/admin",produces = {"application/json"})
@CrossOrigin(origins = "*")
public class ProductController {
     @Autowired
     private ProductInsertService productInsertService;
    @Autowired
    private GetProductService getProductService;
    @Autowired
    private GetProdutcsForCodigo getProdutcsForCodigo;

    @PostMapping("/insert")
    public ResponseEntity<String> insert(@RequestBody ProductDto productDto){
        return productInsertService.insertProduct(productDto);
    }
    @PostMapping("/insert-img")
    public String insert(@RequestParam("file")MultipartFile file){
        return productInsertService.insertProduct(file);
    }
    @GetMapping("/findall")
    public ResponseEntity<List<Products>>getProd(){
        return getProductService.getProducts();
    }
    @GetMapping("findbyid")
    public ResponseEntity<Products> getProductsById(@RequestParam(name="id") Long id){
        return getProductService.getProductsById(id);
    }
    @GetMapping("codigo")
    public Products getProductForCodigo(@RequestParam(name="codigo")Long codigo){
        return getProdutcsForCodigo.getProduct(codigo);
    }
    @GetMapping
    public void getImageProduct(){

    }
}
