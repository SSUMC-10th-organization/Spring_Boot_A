package com.example.umc10th.domain.member.service;

import com.example.umc10th.domain.member.converter.MemberConverter;
import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.exception.MemberException;
import com.example.umc10th.domain.member.exception.code.MemberErrorCode;
import com.example.umc10th.domain.member.repository.MemberRepository;
import com.example.umc10th.global.entity.AuthMember;
import com.example.umc10th.global.security.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Transactional
    public MemberResDTO.SignupResult signup(MemberReqDTO.Signup request) {
        String encodedPassword = passwordEncoder.encode(request.password());
        Member member = MemberConverter.toMember(request, encodedPassword);
        Member savedMember = memberRepository.save(member);

        return new MemberResDTO.SignupResult(
                savedMember.getId(),
                savedMember.getNickname()
        );
    }

    public MemberResDTO.Login login(MemberReqDTO.Login request) {
        Member member = memberRepository.findByEmail(request.email())
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        if (!passwordEncoder.matches(request.password(), member.getPassword())) {
            throw new MemberException(MemberErrorCode.WRONG_PASSWORD);
        }

        String accessToken = jwtUtil.createAccessToken(new AuthMember(member));
        return new MemberResDTO.Login(accessToken);
    }

    public MemberResDTO.MyPageResult getMyPage(AuthMember authMember) {
        Member member = authMember.getMember();

        return new MemberResDTO.MyPageResult(
                member.getNickname(),
                member.getEmail(),
                member.getPhone(),
                member.getPoint()
        );
    }
}
