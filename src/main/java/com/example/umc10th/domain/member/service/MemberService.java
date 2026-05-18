package com.example.umc10th.domain.member.service;

import com.example.umc10th.domain.member.converter.MemberConverter;
import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;  // 추가

    // 회원가입
    @Transactional
    public MemberResDTO.SignupResult signup(MemberReqDTO.Signup request) {
        String encodedPassword = passwordEncoder.encode(request.password());  // 비밀번호 인코딩
        Member member = MemberConverter.toMember(request, encodedPassword);   // Converter 사용

        Member savedMember = memberRepository.save(member);

        return new MemberResDTO.SignupResult(
                savedMember.getId(),
                savedMember.getNickname()
        );
    }

    // 마이페이지 조회
    public MemberResDTO.MyPageResult getMyPage(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 회원입니다."));

        return new MemberResDTO.MyPageResult(
                member.getNickname(),
                member.getEmail(),
                member.getPhone(),
                member.getPoint()
        );
    }
}