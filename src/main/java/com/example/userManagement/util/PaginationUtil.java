package com.example.userManagement.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class PaginationUtil {


    public Pageable paginationUtil(Integer pageNumber, Integer pageSize){
        if(pageSize ==null || pageSize==0 || pageNumber ==null){
            pageSize=Integer.MAX_VALUE;
            pageNumber=0;
        }
        return  PageRequest.of(pageNumber,pageSize);
    }
}
