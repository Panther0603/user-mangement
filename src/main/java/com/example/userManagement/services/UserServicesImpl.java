package com.example.userManagement.services;

import com.example.userManagement.dto.CommonDTO;
import com.example.userManagement.entity.User;
import com.example.userManagement.repository.UserRepository;
import com.example.userManagement.util.PaginationUtil;
import com.example.userManagement.util.ReturnMessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServicesImpl implements UserServices {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    @Lazy
    PaginationUtil paginationUtil;

    @Autowired
    @Lazy
    ReturnMessageUtil returnMessageUtil;

    @Override
    public CommonDTO<User> getUsers(Integer pageNumber, Integer pageSize, Boolean active) {
        Pageable pageable=this.paginationUtil.paginationUtil(pageNumber,pageSize);
        Page<User> users=null;
       if(active!=null)
           users=this.userRepository.findByActive(pageable,active);
       else
           users=this.userRepository.findAll(pageable);

        CommonDTO<User> commonDTO=new CommonDTO<>();
        commonDTO.setDataList(users.getContent());
        return commonDTO;
    }

    @Override
    public CommonDTO<User> createUsers(User user) {

        user.setUserUid(UUID.randomUUID().toString());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user=this.userRepository.save(user);
        CommonDTO<User> commonDTO=new CommonDTO<>();
        commonDTO.setData(user);
        return commonDTO;
    }

    @Override
    public CommonDTO<User> updateUsers(User user) {
        CommonDTO<User> commonDTO = new CommonDTO<>();
        Optional<User> optionalUser = userRepository.findByUserUid(user.getUserUid());
        if (optionalUser.isPresent()) {
            User existingUser = optionalUser.get();
            existingUser.setEmail(user.getEmail());
            existingUser.setActive(user.getActive());
            this.userRepository.save(existingUser);
            commonDTO.setData(existingUser);
            commonDTO=this.returnMessageUtil.updatedSuccessFully("User",commonDTO);
        } else {
            commonDTO=this.returnMessageUtil.setNotFound("User",commonDTO);
        }
        return commonDTO;
    }

    @Override
    public CommonDTO<User> deleteUsers(User user) {
        return null;
    }

    @Override
    public CommonDTO<User> getById(String uid) {
        return null;
    }

}
