package com.example.userManagement.services;

import com.example.userManagement.entity.User;
import com.example.userManagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserServicesImpl implements UserServices {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<User> getUsers() {
        return this.userRepository.findAll();
    }

    @Override
    public User createUsers(User user) {
        user.setUserUid(UUID.randomUUID().toString());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return this.userRepository.save(user);

    }



}
