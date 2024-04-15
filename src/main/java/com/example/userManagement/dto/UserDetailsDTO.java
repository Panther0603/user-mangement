package com.example.userManagement.dto;


import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDetailsDTO {

    private String uid;
    private String username;
    private String password;
    private String email;
    private Date   createdDate;
    private String name;
    private Boolean active;

}
