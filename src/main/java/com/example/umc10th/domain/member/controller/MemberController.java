package com.example.umc10th.domain.member.controller;

import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.service.MemberService;
import com.example.umc10th.global.handler.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.example.umc10th.global.handler.SuccessStatus;

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

    @GetMapping("/me")
    public ApiResponse<MemberResDTO.MyPageResult> getMyPage(
            @RequestParam Long memberId
    ) {
        return ApiResponse.onSuccess(SuccessStatus._OK, memberService.getMyPage(memberId));
    }
}