package com.example.userManagement.dto;
import lombok.Data;

import java.util.List;

@Data
public class CommonDTO <T> {

    private T data;
    private List<T> dataList;
    private String message;
    private Integer httpCode;
    private boolean status;

}
