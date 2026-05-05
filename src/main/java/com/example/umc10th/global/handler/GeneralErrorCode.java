package com.example.umc10th.global.handler;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GeneralErrorCode implements BaseErrorCode {

    NOT_FOUND(404, "COMMON404", "리소스를 찾을 수 없습니다"),
    UNAUTHORIZED(401, "COMMON401", "인증이 필요합니다"),
    FORBIDDEN(403, "COMMON403", "권한이 없습니다"),
    INTERNAL_ERROR(500, "COMMON500", "서버 내부 오류입니다"),
    BAD_REQUEST(400, "COMMON400", "잘못된 요청입니다");

    private final int status;
    private final String code;
    private final String message;
}