package org.example.umc10th_m4.domain.member.service;

import org.example.umc10th_m4.domain.member.dto.MemberResponseDto;

public interface MemberService {
    MemberResponseDto getMember(long memberId);
}
