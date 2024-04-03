package com.example.userManagement.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class AuthRequest {

    private String email;
    private String password;
}
