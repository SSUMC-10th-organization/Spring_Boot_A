package org.example.umc10th_m4.domain.member.dto;

import lombok.Getter;

@Getter
public class MemberSignupRequestDto {
    private String email;
    private String password;
    private String name;
    private String number;
}