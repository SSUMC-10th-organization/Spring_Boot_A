package org.example.umc10th_m4.domain.member.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberResponseDto {
    private long userId;
    private String name;
    private String email;
    private int point;
    private String token;
    private String createdAt;
}