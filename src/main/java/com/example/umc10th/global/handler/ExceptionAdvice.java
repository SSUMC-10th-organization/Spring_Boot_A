package com.example.umc10th.global.handler;

import com.example.umc10th.global.exception.GeneralException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionAdvice {

    // 기존 핸들러 유지
    @ExceptionHandler(GeneralException.class)
    public ApiResponse<?> handleGeneralException(GeneralException e) {
        return ApiResponse.onFailure(e.getCode(), null);
    }

    @ExceptionHandler(Exception.class)
    public ApiResponse<?> handleException(Exception e) {
        return ApiResponse.onFailure(GeneralErrorCode.INTERNAL_ERROR, null);
    }

    // @Valid 검증 실패 핸들러 추가
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Map<String, String>>>
    handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {

        // 검증 실패한 필드명 : 에러메시지 담기
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getFieldErrors()
                .forEach(error -> errors.put(
                        error.getField(),
                        error.getDefaultMessage()
                ));

        return ResponseEntity
                .status(400)
                .body(ApiResponse.onFailure(GeneralErrorCode.BAD_REQUEST, errors));
    }
}