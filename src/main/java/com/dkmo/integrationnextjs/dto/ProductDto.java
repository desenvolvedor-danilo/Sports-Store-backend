package com.dkmo.integrationnextjs.dto;

public record ProductDto(Long codigo,String categoria, String nome,String nomeImagem,String edicao,double precoAntigo, double precoNovo,double parcelado) {

}
