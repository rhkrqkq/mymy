package com.example.mymy.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberLoginRequest {
    private String email;
    private String password;
}
