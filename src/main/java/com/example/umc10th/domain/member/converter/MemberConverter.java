package com.example.umc10th.domain.member.converter;

import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.enums.SocialType;
import org.springframework.stereotype.Component;

@Component
public class MemberConverter {

    public static Member toMember(MemberReqDTO.Signup request, String encodedPassword) {
        return Member.builder()
                .name(request.name())
                .nickname(request.nickname())
                .phone(request.phone())
                .gender(request.gender())
                .socialType(SocialType.LOCAL)  // 폼 로그인은 LOCAL
                .email(request.email())
                .password(encodedPassword)     // 이미 인코딩된 비밀번호
                .point(0)
                .build();
    }
}