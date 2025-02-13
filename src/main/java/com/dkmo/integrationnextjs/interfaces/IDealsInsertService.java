package com.dkmo.integrationnextjs.interfaces;

import org.springframework.http.ResponseEntity;

import com.dkmo.integrationnextjs.dto.DealsDto;

public interface IDealsInsertService {
    public ResponseEntity<String> createDeal(DealsDto dealsDto);
}
