package com.example.userManagement.controller;

import com.example.userManagement.dto.AuthRequest;
import com.example.userManagement.dto.AuthResponse;
import com.example.userManagement.entity.User;
import com.example.userManagement.security.JWTHelper;
import com.example.userManagement.services.UserServicesImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    JWTHelper helper;

    @Autowired
    AuthenticationManager manager;

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    UserServicesImpl userServices;
    private Logger logger= LoggerFactory.getLogger(AuthController.class);

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> getToken(@RequestBody AuthRequest request){
        this.doAuthenticate(request.getEmail(), request.getPassword());

        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
        String token = this.helper.generateToken(userDetails);

        AuthResponse response = AuthResponse.builder()
                .token(token)
                .userName(userDetails.getUsername()).build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @PostMapping("/createUser")
    public ResponseEntity<User> crateUser(@RequestBody User user){
        return ResponseEntity.ok(this.userServices.createUsers(user));
    }

    private void doAuthenticate(String email, String password) {

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
        try {
            manager.authenticate(authentication);
        }catch (BadCredentialsException e) {
            throw new BadCredentialsException(" Invalid Username or Password  !!");
        }

    }

    @ExceptionHandler(BadCredentialsException.class)
    public String exceptionHandler() {
        return "Credentials Invalid !!";
    }
}
