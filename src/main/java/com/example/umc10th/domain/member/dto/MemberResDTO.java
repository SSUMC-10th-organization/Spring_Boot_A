package com.example.demo.member.dto;

import java.time.LocalDateTime;

public class MemberResDTO {

    public record SignupResult(
            Long memberId,
            String email,
            String name,
            LocalDateTime createdAt
    ) {}
}
