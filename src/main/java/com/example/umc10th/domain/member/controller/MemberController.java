package com.example.demo.member.controller;

import com.example.demo.global.apiPayload.ApiResponse;
import com.example.demo.global.apiPayload.code.SuccessStatus;
import com.example.demo.member.dto.MemberReqDTO;
import com.example.demo.member.dto.MemberResDTO;
import com.example.demo.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth/members")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public ApiResponse<MemberResDTO.SignupResult> signup(
            @RequestBody MemberReqDTO.Signup request
    ) {
        return ApiResponse.onSuccess(SuccessStatus._OK, memberService.signup(request));
    }
}
