package com.example.umc10th.domain.review.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ReviewSuccessCode implements BaseSuccessCode {

    REVIEW_CREATE_OK(HttpStatus.CREATED, "REVIEW_201", "리뷰 작성 성공"),
    REVIEWS_OK(HttpStatus.OK, "REVIEW_200_1", "내 리뷰 목록 조회 성공");

    private final HttpStatus status;
    private final String code;
    private final String message;
}