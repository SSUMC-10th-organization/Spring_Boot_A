package com.example.umc.global.apiPayload.code;

import org.springframework.http.HttpStatus;

public record ReasonDTO(
        HttpStatus httpStatus,
        Boolean isSuccess,
        String code,
        String message
) {
}
