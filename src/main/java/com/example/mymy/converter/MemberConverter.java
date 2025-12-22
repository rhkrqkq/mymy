package com.example.mymy.converter;

import com.example.mymy.domain.Member;
import com.example.mymy.domain.Role;
import com.example.mymy.web.dto.MemberRequestDTO;

public class MemberConverter {

    public static Member toEntity(MemberRequestDTO.MemberJoinRequest request) {
        return Member.builder()
                .email(request.getEmail())
                .password(request.getPassword())
                .name(request.getName())
                .phone(request.getPhone())
                .address(request.getAddress())
                .role(Role.USER)  // 가입 시 기본 권한은 USER
                .build();
    }
}
