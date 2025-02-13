package com.dkmo.integrationnextjs.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dkmo.integrationnextjs.dto.DealsDto;
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
public ResponseEntity<String> createDeal(@RequestBody DealsDto dealsDto){
    return insertDealService.createDeal(dealsDto);
}
@GetMapping("/findall")
public ResponseEntity<List<Deal>> getAllDeals(){
    return getDealsService.getAllDeals();
}
}
