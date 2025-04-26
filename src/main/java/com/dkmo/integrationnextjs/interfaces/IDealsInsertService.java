package com.dkmo.integrationnextjs.interfaces;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import com.dkmo.integrationnextjs.models.Deal;

public interface IDealsInsertService {
    public ResponseEntity<String> createDeal(Deal deal,MultipartFile [] imagens);
}
