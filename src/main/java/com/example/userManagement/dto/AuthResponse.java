package com.example.userManagement.dto;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {
    private String userName;
    private String token;
    private Boolean isAuthorized;
    private String userUid;
}
