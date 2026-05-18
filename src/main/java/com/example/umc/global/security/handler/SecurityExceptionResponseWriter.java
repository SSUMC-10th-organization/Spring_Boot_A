package com.example.umc.global.security.handler;

import com.example.umc.global.apiPayload.ApiResponse;
import com.example.umc.global.apiPayload.code.BaseErrorCode;
import com.example.umc.global.apiPayload.code.ReasonDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
public class SecurityExceptionResponseWriter {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public void write(HttpServletResponse response, BaseErrorCode errorCode) throws IOException {
        ReasonDTO reason = errorCode.getReasonHttpStatus();
        response.setStatus(reason.httpStatus().value());
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        objectMapper.writeValue(response.getWriter(), ApiResponse.onFailure(errorCode, null));
    }
}
