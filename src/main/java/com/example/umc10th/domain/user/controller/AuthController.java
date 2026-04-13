package com.example.umc10th.domain.user.controller;

import com.example.umc10th.domain.user.dto.UserReqDTO;
import com.example.umc10th.domain.user.dto.UserResDTO;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    // 회원가입
    @PostMapping("/users")
    public ApiResponse<UserResDTO.JoinRes> join(
            @RequestBody UserReqDTO.JoinReq request
    ) {
        return ApiResponse.onSuccess(GeneralSuccessCode.USER_JOIN_OK, null);
    }
}