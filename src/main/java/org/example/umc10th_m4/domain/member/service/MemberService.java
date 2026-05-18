package org.example.umc10th_m4.domain.member.service;

import org.example.umc10th_m4.domain.member.dto.MemberResponseDto;
import org.example.umc10th_m4.domain.member.dto.MemberSignupRequestDto;

public interface MemberService {
    MemberResponseDto getMember(long memberId);
    MemberResponseDto getMemberByEmail(String email);
    MemberResponseDto signUp(MemberSignupRequestDto request);
}
