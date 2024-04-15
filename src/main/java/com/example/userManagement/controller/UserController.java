package com.example.userManagement.controller;

import com.example.userManagement.dto.CommonDTO;
import com.example.userManagement.dto.UserDetailsDTO;
import com.example.userManagement.entity.User;
import com.example.userManagement.services.UserServicesImpl;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserServicesImpl userServices;
    @PostMapping("/create")
    public ResponseEntity<CommonDTO<UserDetailsDTO>> createUser(@RequestBody UserDetailsDTO user){
        return ResponseEntity.ok(this.userServices.createUsers(user));
    }

    @GetMapping("/get")
    public CommonDTO<UserDetailsDTO> getUsers(@RequestParam(required = false,defaultValue = "0") Integer pageNumber,@RequestParam(required = false ,defaultValue = "10") Integer pageSize,@RequestParam(required = false) Boolean active){
        return  this.userServices.getUsers(pageNumber,pageSize,active);
    }

    @GetMapping
    public CommonDTO<UserDetailsDTO> getUserByUidAndEmail( @RequestParam(required = false) String uid,@RequestParam(required = false) String email){
        return  this.userServices.getByUidOrEmail(uid,email);
    }

    @DeleteMapping
    public CommonDTO<UserDetailsDTO> deleteUser( @RequestParam("uid") String uid){
        return  this.userServices.deleteUsers(uid);
    }

    @PutMapping("/update")
    public ResponseEntity<CommonDTO<UserDetailsDTO>> updateuser(@RequestBody UserDetailsDTO user){
        return ResponseEntity.ok(this.userServices.updateUsers(user));
    }
}
