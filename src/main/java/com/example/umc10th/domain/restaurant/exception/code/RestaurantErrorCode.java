package com.example.umc10th.domain.restaurant.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum RestaurantErrorCode implements BaseErrorCode {

    RESTAURANT_NOT_FOUND(HttpStatus.NOT_FOUND, "RESTAURANT404_1", "존재하지 않는 가게입니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
