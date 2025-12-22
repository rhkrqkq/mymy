package com.example.mymy.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

public class MemberRequestDTO {

    @Getter
    @NoArgsConstructor
    public class MemberJoinRequest {
        private String email;
        private String password;
        private String name;
        private String phone;
        private String address;
    }
}
