package com.example.userManagement.util;

import com.example.userManagement.dto.CommonDTO;
import org.springframework.stereotype.Component;

@Component
public class ReturnMessageUtil {


    public <T>CommonDTO<T> setNotFound(String entityName, CommonDTO<T> commonDTO){
        commonDTO.setMessage(entityName+" not found ");
        commonDTO.setStatus(false);
        commonDTO.setHttpCode(204);
        return commonDTO;
    }

    public <T> CommonDTO<T> updatedSuccessFully(String entityName, CommonDTO<T> commonDTO){
        commonDTO.setMessage(entityName+" update successfully ");
        commonDTO.setStatus(true);
        commonDTO.setHttpCode(200);
        return commonDTO;
    }
}
