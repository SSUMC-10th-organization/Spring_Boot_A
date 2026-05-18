package com.example.umc.global.apiPayload.exception.handler;

import com.example.umc.global.apiPayload.ApiResponse;
import com.example.umc.global.apiPayload.code.BaseErrorCode;
import com.example.umc.global.apiPayload.code.ReasonDTO;
import com.example.umc.global.apiPayload.code.status.GeneralErrorCode;
import com.example.umc.global.apiPayload.exception.ProjectException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class GeneralExceptionAdvice {

    @ExceptionHandler(ProjectException.class)
    public ResponseEntity<ApiResponse<Object>> handleProjectException(ProjectException exception) {
        return handleExceptionInternal(exception.getErrorCode(), null);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Object>> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException exception
    ) {
        Map<String, String> errors = new LinkedHashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(error ->
                errors.putIfAbsent(error.getField(), error.getDefaultMessage())
        );
        return handleExceptionInternal(GeneralErrorCode.REQUEST_BODY_INVALID, errors);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiResponse<Object>> handleHttpMessageNotReadableException() {
        return handleExceptionInternal(GeneralErrorCode.REQUEST_BODY_INVALID, null);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiResponse<Object>> handleConstraintViolationException(
            ConstraintViolationException exception
    ) {
        Map<String, String> errors = new LinkedHashMap<>();
        exception.getConstraintViolations().forEach(error ->
                errors.put(error.getPropertyPath().toString(), error.getMessage())
        );
        return handleExceptionInternal(GeneralErrorCode.REQUEST_BODY_INVALID, errors);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Object>> handleException(Exception exception) {
        return handleExceptionInternal(GeneralErrorCode.INTERNAL_SERVER_ERROR, exception.getMessage());
    }

    private ResponseEntity<ApiResponse<Object>> handleExceptionInternal(BaseErrorCode errorCode, Object result) {
        ReasonDTO reason = errorCode.getReasonHttpStatus();
        return ResponseEntity
                .status(reason.httpStatus())
                .body(ApiResponse.onFailure(errorCode, result));
    }
}
