package com.kusitms.backend.service;

import com.kusitms.backend.dto.SignInRequest;
import com.kusitms.backend.dto.TokenDto;

public interface IAuthService {

  TokenDto signIn(SignInRequest request);
}
