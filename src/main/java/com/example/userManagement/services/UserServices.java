package com.example.userManagement.services;



import com.example.userManagement.dto.CommonDTO;
import com.example.userManagement.entity.User;

import java.util.List;

public interface UserServices {

    public CommonDTO<User> getUsers(Integer pageNumber,Integer pageSize,Boolean active);
    public CommonDTO<User> createUsers(User  user);
    public CommonDTO<User> updateUsers(User  user);
    public CommonDTO<User> deleteUsers(User  user);
    public CommonDTO<User> getById(String uid);

}
