package com.example.umc10th.domain.member.controller;

import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.service.MemberService;
import com.example.umc10th.global.entity.AuthMember;
import com.example.umc10th.global.handler.ApiResponse;
import com.example.umc10th.global.handler.SuccessStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public ApiResponse<MemberResDTO.SignupResult> signup(
            @RequestBody MemberReqDTO.Signup request
    ) {
        return ApiResponse.onSuccess(SuccessStatus._OK, memberService.signup(request));
    }

    @PostMapping("/login")
    public ApiResponse<MemberResDTO.Login> login(
            @RequestBody MemberReqDTO.Login request
    ) {
        return ApiResponse.onSuccess(SuccessStatus._OK, memberService.login(request));
    }

    @GetMapping("/me")
    public ApiResponse<MemberResDTO.MyPageResult> getMyPage(
            @AuthenticationPrincipal AuthMember authMember
    ) {
        return ApiResponse.onSuccess(SuccessStatus._OK, memberService.getMyPage(authMember));
    }
}
