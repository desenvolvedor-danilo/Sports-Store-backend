package com.dkmo.integrationnextjs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dkmo.integrationnextjs.models.Deal;

public interface DealsRepository extends JpaRepository<Deal,Long>{

}
