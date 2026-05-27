package com.example.umc.domain.auth.controller;

import com.example.umc.domain.user.code.UserSuccessCode;
import com.example.umc.domain.user.dto.UserRequestDTO;
import com.example.umc.domain.user.dto.UserResponseDTO;
import com.example.umc.domain.user.service.UserService;
import com.example.umc.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Auth", description = "인증 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthRestController {

    private final UserService userService;

    @Operation(summary = "회원가입 API", description = "폼 로그인을 위한 이메일과 BCrypt로 암호화할 비밀번호를 입력받습니다.")
    @PostMapping("/signup")
    public ApiResponse<UserResponseDTO.SignUpResponse> signUp(
            @RequestBody @Valid UserRequestDTO.SignUpRequest request
    ) {
        UserResponseDTO.SignUpResponse response = userService.signUp(request);
        return ApiResponse.of(UserSuccessCode.USER_SIGNED_UP, response);
    }

    @Operation(summary = "로그인 API", description = "이메일과 비밀번호 검증 후 JWT Access Token을 발급합니다.")
    @PostMapping("/login")
    public ApiResponse<UserResponseDTO.LoginResponse> login(
            @RequestBody @Valid UserRequestDTO.LoginRequest request
    ) {
        UserResponseDTO.LoginResponse response = userService.login(request);
        return ApiResponse.of(UserSuccessCode.USER_LOGGED_IN, response);
    }
}
