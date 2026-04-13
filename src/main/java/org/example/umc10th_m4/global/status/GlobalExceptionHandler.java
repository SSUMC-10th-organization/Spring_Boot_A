package org.example.umc10th_m4.global.status;

import org.example.umc10th_m4.global.common.ApiResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(GeneralException.class)
    public ApiResponse<Object> onThrowException(GeneralException generalException) {
        BaseStatus errorCode = generalException.getCode();
        return ApiResponse.onFailure(errorCode.getCode(), errorCode.getMessage(), null);
    }

    @ExceptionHandler(Exception.class)
    public ApiResponse<Object> exception(Exception e) {
        return ApiResponse.onFailure(
                ErrorStatus.INTERNAL_SERVER_ERROR.getCode(),
                ErrorStatus.INTERNAL_SERVER_ERROR.getMessage() + " (" + e.getMessage() + ")",
                null
        );
    }
}
