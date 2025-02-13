package com.dkmo.integrationnextjs.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.dkmo.integrationnextjs.interfaces.IGetAllDeals;
import com.dkmo.integrationnextjs.models.Deal;
import com.dkmo.integrationnextjs.repository.DealsRepository;

@Service
public class GetDealsService  implements IGetAllDeals{
   @Autowired
   private DealsRepository dealsRepository;
    @Override
    public ResponseEntity<List<Deal>> getAllDeals() {
        List<Deal> deals = dealsRepository.findAll();
        return ResponseEntity.ok().body(deals);
    }
    
}
