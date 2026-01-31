package com.example.mymy.service;

import com.example.mymy.converter.MemberConverter;
import com.example.mymy.domain.Member;
import com.example.mymy.global.GlobalExceptionHandler;
import com.example.mymy.repository.MemberRepository;
import com.example.mymy.web.dto.MemberRequestDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    // 회원가입
    public Long join(MemberRequestDTO.MemberJoinRequest request) {
        memberRepository.findByEmail(request.getEmail())
                .ifPresent(m -> {
                    throw new GlobalExceptionHandler.BusinessException(GlobalExceptionHandler.ErrorType.DUPLICATE_EMAIL);
                });

        // Member 엔티티
        Member member = MemberConverter.toEntity(request);
        return memberRepository.save(member).getMemberId();
    }

    // 로그인
    public Long login(MemberRequestDTO.MemberLoginRequest request) {
        // 이메일로 회원 조회
        Member member = memberRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new GlobalExceptionHandler.BusinessException(GlobalExceptionHandler.ErrorType.ACCESS_DENIED));

        // 비밀번호 일치 확인
        if (!passwordEncoder.matches(request.getPassword(), member.getPassword())) {
            throw new GlobalExceptionHandler.BusinessException(GlobalExceptionHandler.ErrorType.ACCESS_DENIED);
        }

        return member.getMemberId();
    }
}
