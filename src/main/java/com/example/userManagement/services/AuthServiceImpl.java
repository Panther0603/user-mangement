package com.example.userManagement.services;

import com.example.userManagement.dto.AuthRequest;
import com.example.userManagement.dto.AuthResponse;
import com.example.userManagement.security.JWTHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthServices{

    @Autowired
    AuthenticationManager manager;

    @Autowired
    JWTHelper jwtHelper;

    @Autowired
    UserDetailsService userDetailsService;
    @Override
    public AuthResponse getToken(AuthRequest authRequest) {
        return null;
    }

    private void doAuthenticate(String email, String password) {

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
        try {
            manager.authenticate(authentication);


        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(" Invalid Username or Password  !!");
        }

    }
}
