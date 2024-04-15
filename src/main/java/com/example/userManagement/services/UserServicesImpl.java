package com.example.userManagement.services;

import com.example.userManagement.dto.CommonDTO;
import com.example.userManagement.dto.UserDetailsDTO;
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

import java.util.*;
import java.util.stream.Collectors;

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
    public CommonDTO<UserDetailsDTO> getUsers(Integer pageNumber, Integer pageSize, Boolean active) {
        Pageable pageable=this.paginationUtil.paginationUtil(pageNumber,pageSize);
        Page<User> users=null;
       if(active!=null)
           users=this.userRepository.findByActive(pageable,active);
       else
           users=this.userRepository.findAll(pageable);

        CommonDTO<UserDetailsDTO> commonDTO=new CommonDTO<>();
        commonDTO.setDataList(mapUserListData(users));
        return commonDTO;
    }

    @Override
    public CommonDTO<UserDetailsDTO> createUsers(UserDetailsDTO userDetailsDTO) {

//        user.setUserUid(UUID.randomUUID().toString());
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        user=this.userRepository.save(user);
        User existingUser =null;
        CommonDTO<UserDetailsDTO> commonDTO= new CommonDTO<>();
        Optional<User> optionalUser = userRepository.findByUserUid(userDetailsDTO.getUid());
        if(optionalUser.isPresent()) {
            existingUser = optionalUser.get();
            existingUser=dtoToEntity(existingUser,userDetailsDTO);
            commonDTO.setData(entityToDTO(existingUser));
            commonDTO=this.returnMessageUtil.updatedSuccessFully("User",commonDTO);
        } else {
            existingUser = new User();
            existingUser = dtoToEntity(existingUser,userDetailsDTO);
            commonDTO=this.returnMessageUtil.setNotFound("User created",commonDTO);
        }
        this.userRepository.save(existingUser);
        commonDTO.setData(entityToDTO(existingUser));
        return commonDTO;
    }

    @Override
    public CommonDTO<UserDetailsDTO> updateUsers(UserDetailsDTO userDetailsDTO) {
        CommonDTO<UserDetailsDTO> commonDTO = new CommonDTO<>();
        Optional<User> optionalUser = userRepository.findByUserUid(userDetailsDTO.getUid());
        if (optionalUser.isPresent()) {
            User existingUser = optionalUser.get();
            existingUser=dtoToEntity(existingUser,userDetailsDTO);
            this.userRepository.save(existingUser);
            commonDTO.setData(entityToDTO(existingUser));
            commonDTO=this.returnMessageUtil.updatedSuccessFully("User",commonDTO);
        } else {
            commonDTO=this.returnMessageUtil.setNotFound("User",commonDTO);
        }
        return commonDTO;
    }

    @Override
    public CommonDTO<UserDetailsDTO> deleteUsers(String uid) {
       CommonDTO<UserDetailsDTO> commonDTO=new CommonDTO<>();
       Optional<User> optionalUser= this.userRepository.findByUserUid(uid);
       if(optionalUser.isPresent()){
           User user=optionalUser.get();
           user.setActive(false);
           this.userRepository.save(user);
           commonDTO.setData(entityToDTO(user));
           commonDTO=this.returnMessageUtil.deletedSuccessFully("User ",commonDTO);
       }else {
           commonDTO=this.returnMessageUtil.setNotFound("User ",commonDTO);
       }
        return commonDTO;
    }

    @Override
    public CommonDTO<UserDetailsDTO> getByUidOrEmail(String uid,String email) {
        CommonDTO<UserDetailsDTO> commonDTO=new CommonDTO<>();
        Optional<User> optionalUser=null;
         if(uid!=null){
             optionalUser=this.userRepository.findByUserUid(uid);
         } else if (email!=null) {
             optionalUser=this.userRepository.findByEmail(email);
         }
        if(optionalUser!=null && optionalUser.isPresent()){
            User user=optionalUser.get();
            user.setActive(false);
            this.userRepository.save(user);
            commonDTO.setData(entityToDTO(user));
            commonDTO=this.returnMessageUtil.recordsFetchedSuccessFully("User ",commonDTO);
        }else {
            commonDTO=this.returnMessageUtil.setNotFound("User ",commonDTO);
        }
        return commonDTO;
    }

    public UserDetailsDTO entityToDTO(User user){

        return UserDetailsDTO.builder()
                .name(user.getName())
                .uid(user.getUserUid())
                .active(user.getActive())
                .username(user.getUsername())
                .email(user.getEmail())
                .createdDate(user.getCreatedDate())
                .build();
    }


    public List<UserDetailsDTO> mapUserListData(Page<User> users){
        return users.getContent().stream().map((user -> UserDetailsDTO.builder().
                name(user.getName())
                .active(user.getActive()).
                createdDate(user.getCreatedDate()).
                email(user.getEmail()).
                username(user.getUsername()).build()))
                .collect(Collectors.toList());
    }

    public User dtoToEntity(User user,UserDetailsDTO userDetailsDTO){
        if(user.getUserUid()==null){
           user.setUserUid(UUID.randomUUID().toString());
           user.setCreatedDate(new Date());
        }
        if(userDetailsDTO.getActive()!=null){
            user.setActive(userDetailsDTO.getActive());
        }else{
            user.setActive(true);
        }

        if(userDetailsDTO.getName()!=null && !userDetailsDTO.getName().isEmpty()){
            user.setName(userDetailsDTO.getName());
        }

        if(userDetailsDTO.getEmail()!=null && !userDetailsDTO.getEmail().isEmpty()){
            user.setEmail(userDetailsDTO.getEmail());
        }
        if(user.getPassword()==null && (userDetailsDTO.getPassword()!=null && !userDetailsDTO.getPassword().isEmpty())){
            user.setPassword(passwordEncoder.encode(userDetailsDTO.getPassword()));
        }

        return user;
    }

}
