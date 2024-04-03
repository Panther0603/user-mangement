package com.example.userManagement.controller;

import com.example.userManagement.entity.User;
import com.example.userManagement.services.UserServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/home")
public class HomeController {

    @Autowired
    UserServicesImpl userServices;
    @GetMapping("/user")
    public List<User> getUser(){
        return  this.userServices.getUsers();
    }


    @GetMapping("/current-user")
    public String getLoggedInUser(Principal principal){
        return principal.getName();
    }
}
