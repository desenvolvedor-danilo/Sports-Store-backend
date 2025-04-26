package com.dkmo.integrationnextjs.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.dkmo.integrationnextjs.dto.ResponseDealDto;
import com.dkmo.integrationnextjs.models.Deal;
import com.dkmo.integrationnextjs.services.DealInsertService;
import com.dkmo.integrationnextjs.services.GetDealsService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/deal")
public class DealsController {
@Autowired
private DealInsertService insertDealService;
@Autowired
private GetDealsService getDealsService;
@PostMapping("/create")
public ResponseEntity<String> createDeal(@RequestParam(name="titulo") String titulo,@RequestParam(name="nome") String nome,@RequestParam(name="valor") String valor, @RequestParam(name="imagens") MultipartFile [] imagens){
    Deal deal = new Deal();
    deal.setTitulo(titulo);
    deal.setNome(nome);
    deal.setValor(valor);
    return insertDealService.createDeal(deal,imagens);
}
@GetMapping("/findall")
public List<Deal> getAllDeals(){
    return getDealsService.getAllDeals();
}
@GetMapping("/findbycodigo")
public ResponseDealDto getByCodigo(@RequestParam(name = "codigo")Long codigo){
    return getDealsService.searchCodigo(codigo);
}
}
