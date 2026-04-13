package org.example.umc10th_m4.domain.member.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.umc10th_m4.global.status.BaseStatus;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MemberErrorStatus implements BaseStatus {

    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "MEMBER404", "존재하지 않는 회원입니다"),
    EMAIL_ALREADY_EXIST(HttpStatus.BAD_REQUEST, "MEMBER4001", "이미 존재하는 이메일입니다");
    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
