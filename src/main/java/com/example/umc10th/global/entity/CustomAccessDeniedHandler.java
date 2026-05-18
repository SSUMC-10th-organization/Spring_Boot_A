package com.example.umc10th.global.entity;

import com.example.umc10th.global.handler.ApiResponse;
import com.example.umc10th.global.handler.GeneralErrorCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.io.IOException;

public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();

        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_FORBIDDEN); // 403

        ApiResponse<Void> errorResponse = ApiResponse.onFailure(
                GeneralErrorCode.FORBIDDEN, null
        );

        objectMapper.writeValue(response.getOutputStream(), errorResponse);
    }
}