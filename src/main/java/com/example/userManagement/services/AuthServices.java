package com.example.userManagement.services;

import com.example.userManagement.dto.AuthRequest;
import com.example.userManagement.dto.AuthResponse;

public interface AuthServices {

    public AuthResponse getToken(AuthRequest authRequest);
}
