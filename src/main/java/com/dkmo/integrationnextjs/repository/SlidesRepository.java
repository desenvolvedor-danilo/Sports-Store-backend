package com.dkmo.integrationnextjs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dkmo.integrationnextjs.models.Slides;

public interface SlidesRepository extends JpaRepository<Slides,Long>{
    public Slides findByCodigo(String codigo);
}
