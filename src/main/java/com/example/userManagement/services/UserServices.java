package com.example.userManagement.services;



import com.example.userManagement.dto.CommonDTO;
import com.example.userManagement.dto.UserDetailsDTO;
import com.example.userManagement.entity.User;

import java.util.List;

public interface UserServices {

    public CommonDTO<UserDetailsDTO> getUsers(Integer pageNumber, Integer pageSize, Boolean active);
    public CommonDTO<UserDetailsDTO> createUsers(UserDetailsDTO  user);
    public CommonDTO<UserDetailsDTO> updateUsers(UserDetailsDTO  user);
    public CommonDTO<UserDetailsDTO> deleteUsers(String  uid);
    public CommonDTO<UserDetailsDTO> getByUidOrEmail(String uid,String email);

}
