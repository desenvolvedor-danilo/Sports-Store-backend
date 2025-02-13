package com.dkmo.integrationnextjs.services;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.dkmo.integrationnextjs.dto.DealsDto;
import com.dkmo.integrationnextjs.interfaces.IDealsInsertService;
import com.dkmo.integrationnextjs.models.Deal;
import com.dkmo.integrationnextjs.repository.DealsRepository;

@Service
public class DealInsertService implements IDealsInsertService {
    @Autowired
    private DealsRepository dealsRepository;


    @Override
    public ResponseEntity<String> createDeal(DealsDto dealsDto) {
        Deal deal = new Deal();
        BeanUtils.copyProperties(dealsDto, deal);
        dealsRepository.save(deal);
        return ResponseEntity.ok().body("created with successfuly");
    }
    
}
