package org.example.umc10th_m4.global.status;

import org.example.umc10th_m4.global.common.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(GeneralException.class)
    public ApiResponse<Object> onThrowException(GeneralException generalException) {
        BaseStatus errorCode = generalException.getCode();
        return ApiResponse.onFailure(errorCode.getCode(), errorCode.getMessage(), null);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Map<String, String>>> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException e) {
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));

        return ResponseEntity.status(ErrorStatus.BAD_REQUEST.getHttpStatus())
                .body(ApiResponse.onFailure(
                        ErrorStatus.BAD_REQUEST.getCode(),
                        ErrorStatus.BAD_REQUEST.getMessage(),
                        errors));
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
