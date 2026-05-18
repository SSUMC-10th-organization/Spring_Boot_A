package com.example.umc10th.global.entity;

import com.example.umc10th.global.handler.ApiResponse;
import com.example.umc10th.global.handler.GeneralErrorCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;

public class CustomEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();

        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401

        ApiResponse<Void> errorResponse = ApiResponse.onFailure(
                GeneralErrorCode.UNAUTHORIZED, null
        );

        objectMapper.writeValue(response.getOutputStream(), errorResponse);
    }
}