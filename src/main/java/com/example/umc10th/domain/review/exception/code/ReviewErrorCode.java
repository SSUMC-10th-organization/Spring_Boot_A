package com.example.umc10th.domain.review.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ReviewErrorCode implements BaseErrorCode {

    REVIEW_NOT_FOUND(HttpStatus.NOT_FOUND, "REVIEW404_1", "존재하지 않는 리뷰입니다."),
    INVALID_SORT_TYPE(HttpStatus.BAD_REQUEST, "REVIEW400_1", "정렬 기준은 id 또는 star만 허용됩니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}