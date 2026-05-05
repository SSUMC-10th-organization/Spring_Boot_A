package com.example.umc10th.domain.member.dto;

import com.example.umc10th.domain.member.enums.Gender;
import com.example.umc10th.domain.member.enums.SocialType;

public class MemberReqDTO {

    public record Signup(
            String name,
            String nickname,
            String phone,
            Gender gender,
            SocialType socialType
    ) {}
}