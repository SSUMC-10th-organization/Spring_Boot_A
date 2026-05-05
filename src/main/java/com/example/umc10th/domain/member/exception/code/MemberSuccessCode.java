package com.example.umc10th.domain.member.exception.code;

import com.example.umc10th.global.handler.BaseCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MemberSuccessCode implements BaseCode {

    MEMBER_SIGNUP_SUCCESS("MEMBER200", "회원가입에 성공했습니다"),
    MEMBER_LOGIN_SUCCESS("MEMBER201", "로그인에 성공했습니다");

    private final String code;
    private final String message;
}