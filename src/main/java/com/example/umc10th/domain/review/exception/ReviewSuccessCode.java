package com.example.umc10th.domain.review.exception;

import com.example.umc10th.global.handler.BaseCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ReviewSuccessCode implements BaseCode {

    REVIEW_SUCCESS("REVIEW200", "리뷰 성공입니다");

    private final String code;
    private final String message;
}