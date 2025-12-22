package com.example.mymy.web.controller;

import com.example.mymy.service.MemberService;
import com.example.mymy.web.dto.MemberRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/join")
    public ResponseEntity<String> join(@RequestBody MemberRequestDTO.MemberJoinRequest request) {
        memberService.join(request);
        return ResponseEntity.ok("회원가입이 성공적으로 완료되었습니다.");
    }
}
