package com.example.userManagement.services;



import com.example.userManagement.entity.User;

import java.util.List;

public interface UserServices {

    public List<User> getUsers();
    public User createUsers(User  user);

}
