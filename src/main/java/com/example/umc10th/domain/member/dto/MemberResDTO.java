package com.example.umc10th.domain.member.dto;

public class MemberResDTO {

    public record SignupResult(
            Long memberId,
            String nickname
    ) {}

    public record MyPageResult(
            String nickname,
            String email,
            String phone,
            Integer point
    ) {}

    public record Login(
            String accessToken
    ) {}
}