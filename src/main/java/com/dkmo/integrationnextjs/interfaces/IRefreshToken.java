package com.dkmo.integrationnextjs.interfaces;

import com.dkmo.integrationnextjs.dto.TokenDto;

public interface IRefreshToken {
    public TokenDto refreshToken(String token);
}
