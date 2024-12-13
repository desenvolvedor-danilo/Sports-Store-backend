package com.dkmo.integrationnextjs.dto;

import lombok.Builder;

@Builder
public record TokenDto(String token,String refreshToken) {
    
}
