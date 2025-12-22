package com.example.mymy.service;

import com.example.mymy.converter.MemberConverter;
import com.example.mymy.domain.Member;
import com.example.mymy.global.GlobalExceptionHandler;
import com.example.mymy.repository.MemberRepository;
import com.example.mymy.web.dto.MemberRequestDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    // 이메일 중복 체크
    public Long join(MemberRequestDTO.MemberJoinRequest request) {
        memberRepository.findByEmail(request.getEmail())
                .ifPresent(m -> {
                    throw new GlobalExceptionHandler.BusinessException(GlobalExceptionHandler.ErrorType.DUPLICATE_EMAIL);
                });

        // Member 엔티티
        Member member = MemberConverter.toEntity(request);
        return memberRepository.save(member).getMemberId();
    }
}
