package com.shop.altenshop.services;

import com.shop.altenshop.DTOs.AuthenticationRequest;
import com.shop.altenshop.DTOs.AuthenticationResponse;
import com.shop.altenshop.DTOs.RegisterRequest;

public interface AuthenticationService {
     AuthenticationResponse register(RegisterRequest request);
     AuthenticationResponse authenticate(AuthenticationRequest request);

}
