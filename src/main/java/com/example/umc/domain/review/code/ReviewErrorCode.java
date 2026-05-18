package com.example.umc.domain.review.code;

import com.example.umc.global.apiPayload.code.BaseErrorCode;
import com.example.umc.global.apiPayload.code.ReasonDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ReviewErrorCode implements BaseErrorCode {

    USER_MISSION_NOT_FOUND(HttpStatus.NOT_FOUND, "REVIEW4001", "리뷰를 작성할 유저 미션을 찾을 수 없습니다."),
    REVIEW_ALREADY_EXISTS(HttpStatus.BAD_REQUEST, "REVIEW4002", "이미 리뷰가 작성된 미션입니다."),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "REVIEW4003", "유저를 찾을 수 없습니다."),
    INVALID_CURSOR(HttpStatus.BAD_REQUEST, "REVIEW4004", "커서 형식이 올바르지 않습니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ReasonDTO getReason() {
        return new ReasonDTO(null, false, code, message);
    }

    @Override
    public ReasonDTO getReasonHttpStatus() {
        return new ReasonDTO(httpStatus, false, code, message);
    }
}
