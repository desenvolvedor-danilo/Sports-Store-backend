package com.dkmo.integrationnextjs.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.dkmo.integrationnextjs.interfaces.IDealsInsertService;
import com.dkmo.integrationnextjs.models.Deal;
import com.dkmo.integrationnextjs.repository.DealsRepository;
import com.dkmo.integrationnextjs.utils.ProductsFilesSave;


@Service
public class DealInsertService implements IDealsInsertService {
    @Autowired
    private DealsRepository dealsRepository;
    ProductsFilesSave productsFilesSave= new ProductsFilesSave();
    @Override
    public ResponseEntity<String> createDeal(Deal deals,MultipartFile [] imagens) {
        StringBuilder caminho = new StringBuilder();
        for(MultipartFile imagem: imagens){
        String caminhoImagem = productsFilesSave.saveFile(imagem);
        caminho.append(caminhoImagem).append(",");
        }
        deals.setCaminho(caminho.toString());
        dealsRepository.save(deals);
        return ResponseEntity.ok().body("created with successfuly");
    }
    
}
