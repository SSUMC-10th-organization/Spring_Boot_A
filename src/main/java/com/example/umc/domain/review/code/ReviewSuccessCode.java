package com.example.umc.domain.review.code;

import com.example.umc.global.apiPayload.code.BaseSuccessCode;
import com.example.umc.global.apiPayload.code.ReasonDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ReviewSuccessCode implements BaseSuccessCode {

    REVIEW_CREATED(HttpStatus.CREATED, "REVIEW200_1", "성공적으로 리뷰를 작성했습니다."),
    STORE_REVIEWS_FOUND(HttpStatus.OK, "REVIEW200_2", "성공적으로 가게 리뷰 목록을 조회했습니다."),
    MY_REVIEWS_FOUND(HttpStatus.OK, "REVIEW200_3", "성공적으로 내가 작성한 리뷰 목록을 조회했습니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ReasonDTO getReason() {
        return new ReasonDTO(null, true, code, message);
    }

    @Override
    public ReasonDTO getReasonHttpStatus() {
        return new ReasonDTO(httpStatus, true, code, message);
    }
}
