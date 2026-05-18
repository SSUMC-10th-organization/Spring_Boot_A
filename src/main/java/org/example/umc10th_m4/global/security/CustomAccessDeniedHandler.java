package org.example.umc10th_m4.global.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.umc10th_m4.global.common.ApiResponse;
import org.example.umc10th_m4.global.status.ErrorStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.io.IOException;

public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void handle(
            HttpServletRequest request,
            HttpServletResponse response,
            AccessDeniedException accessDeniedException
    ) throws IOException {
        ErrorStatus code = ErrorStatus.FORBIDDEN;
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(code.getHttpStatus().value());
        objectMapper.writeValue(response.getOutputStream(), ApiResponse.onFailure(code, null));
    }
}
