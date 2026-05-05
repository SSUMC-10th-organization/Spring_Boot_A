package com.example.umc10th.domain.review.exception;

import com.example.umc10th.global.handler.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ReviewErrorCode implements BaseErrorCode {

    REVIEW_NOT_FOUND(404, "REVIEW404", "존재하지 않는 리뷰입니다"),
    REVIEW_ALREADY_EXISTS(400, "REVIEW400", "이미 작성한 리뷰입니다");

    private final int status;
    private final String code;
    private final String message;
}