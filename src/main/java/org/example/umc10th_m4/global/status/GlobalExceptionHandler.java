package org.example.umc10th_m4.global.status;

import org.example.umc10th_m4.global.common.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // GeneralException: 도메인 에러 (404, 400 등) → HTTP 상태코드 함께 반환
    @ExceptionHandler(GeneralException.class)
    public ResponseEntity<ApiResponse<Object>> onThrowException(GeneralException generalException) {
        BaseStatus errorCode = generalException.getCode();
        return ResponseEntity.status(errorCode.getHttpStatus())
                .body(ApiResponse.onFailure(errorCode.getCode(), errorCode.getMessage(), null));
    }

    // @Valid 검증 실패 → HTTP 400 + 필드별 에러 메시지 반환
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

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ApiResponse<Object>> handleNoResourceFoundException(NoResourceFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.onFailure("COMMON404", "존재하지 않는 경로입니다.", null));
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ApiResponse<Object>> handleBadCredentialsException(BadCredentialsException e) {
        return ResponseEntity.status(ErrorStatus.UNAUTHORIZED.getHttpStatus())
                .body(ApiResponse.onFailure(
                        ErrorStatus.UNAUTHORIZED.getCode(),
                        "이메일 또는 비밀번호가 올바르지 않습니다.",
                        null));
    }

    // 그 외 예상치 못한 에러 → HTTP 500
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Object>> exception(Exception e) {
        return ResponseEntity.status(ErrorStatus.INTERNAL_SERVER_ERROR.getHttpStatus())
                .body(ApiResponse.onFailure(
                        ErrorStatus.INTERNAL_SERVER_ERROR.getCode(),
                        ErrorStatus.INTERNAL_SERVER_ERROR.getMessage(),
                        null));
    }
}
