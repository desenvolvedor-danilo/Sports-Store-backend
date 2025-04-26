package com.dkmo.integrationnextjs.interfaces;

import org.springframework.http.ResponseEntity;

public interface IDeleteProduct {
public ResponseEntity<String> deleteProduct(long codigo);
}
