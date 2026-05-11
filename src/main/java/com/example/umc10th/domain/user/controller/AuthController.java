package com.example.umc10th.domain.user.controller;

import com.example.umc10th.domain.user.dto.UserReqDTO;
import com.example.umc10th.domain.user.dto.UserResDTO;
import com.example.umc10th.domain.user.exception.code.UserSuccessCode;
import com.example.umc10th.global.apiPayload.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    // 회원가입
    @PostMapping("/users")
    public ApiResponse<UserResDTO.JoinRes> join(
            @Valid @RequestBody UserReqDTO.JoinReq request
    ) {
        return ApiResponse.onSuccess(UserSuccessCode.USER_JOIN_OK, null);
    }
}