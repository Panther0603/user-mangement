package com.example.userManagement.controller;

import com.example.userManagement.dto.CommonDTO;
import com.example.userManagement.entity.User;
import com.example.userManagement.services.UserServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserServicesImpl userServices;
    @PostMapping("/create")
    public ResponseEntity<CommonDTO<User>> crateUser(@RequestBody User user){
        return ResponseEntity.ok(this.userServices.createUsers(user));
    }

    @GetMapping("/getUsers")
    public CommonDTO<User> getUser(@RequestParam(required = false,defaultValue = "0") Integer pageNumber,@RequestParam(required = false ,defaultValue = "10") Integer pageSize,@RequestParam(required = false) Boolean active){
        return  this.userServices.getUsers(pageNumber,pageSize,active);
    }

}
