package com.dkmo.integrationnextjs.controllers;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dkmo.integrationnextjs.dto.ProductDto;
import com.dkmo.integrationnextjs.models.Products;
import com.dkmo.integrationnextjs.services.DeleteProductService;
import com.dkmo.integrationnextjs.services.EditProductService;
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
    @Autowired
    private DeleteProductService deleteProductService;
    @Autowired
    private EditProductService editProductService;
    @PostMapping("/insert")
    public ResponseEntity<String> insertProduct(
    @RequestParam(name = "codigo")long codigo,
    @RequestParam(name = "categoria")String categoria,
    @RequestParam(name = "nome")String nome,
    @RequestParam(name = "edicao")String edicao,
    @RequestParam(name = "precoAntigo")double precoAntigo,
    @RequestParam(name = "precoNovo")double precoNovo,
    @RequestParam(name = "parcelado")double parcelado,
    @RequestParam("file")MultipartFile file){
    Products products = new Products();
    products.setCodigo(codigo);
    products.setCategoria(categoria);
    products.setEdicao(edicao);
    products.setNome(nome);
    products.setPrecoAntigo(precoAntigo);
    products.setPrecoNovo(precoNovo);
    products.setParcelado(parcelado);
    products.setDesconto(100-precoNovo/precoAntigo*100);
    products.setValorParcela(precoNovo/parcelado);
    return productInsertService.insertProductTeste(products,file);
    }
    @GetMapping("/findall")
    public Page<Products>getProd(@PageableDefault(page = 0, size = 5, sort = "codigo",direction = Sort.Direction.ASC)Pageable pageable){
        return getProductService.getProducts(pageable);
    }
    @GetMapping("/findbyid")
    public ResponseEntity<Products> getProductsById(@RequestParam(name="id") Long id){
        return getProductService.getProductsById(id);
    }
    @GetMapping("/codigo")
    public ResponseEntity<Products> getProductForCodigo(@RequestParam(name="codigo")Long codigo){
        return getProdutcsForCodigo.getProduct(codigo);
    }
    @GetMapping("/find-by-categoria")
    public ResponseEntity<List<Products>> getProductsForCategory(@RequestParam(name="categoria")String categoria){
        return getProductService.getProductsForCategory(categoria);
    }
    @GetMapping("/search")
    public ResponseEntity<List<Products>> getProductsForKeyWord(@RequestParam(name = "find") String key) {
        return getProductService.getProductsForKeyWord(key);
    }
    @PutMapping("/edit-product")
    public ResponseEntity<Products> editProduct(@RequestBody ProductDto productDto) {
        return editProductService.editProdutoService(productDto);
    }
    @DeleteMapping("/delete-product")
    public ResponseEntity<String> deleteProduct(@RequestParam(name = "codigo") long codigo){
        return deleteProductService.deleteProduct(codigo);
    }
}
