package com.dkmo.integrationnextjs.interfaces;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.dkmo.integrationnextjs.models.Deal;

public interface IGetAllDeals {
    public ResponseEntity<List<Deal>> getAllDeals();

}
