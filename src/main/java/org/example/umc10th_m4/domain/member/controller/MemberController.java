package org.example.umc10th_m4.domain.member.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.umc10th_m4.domain.member.dto.*;
import org.example.umc10th_m4.domain.member.service.MemberService;
import org.example.umc10th_m4.global.common.ApiResponse;
import org.example.umc10th_m4.global.security.AuthMember;
import org.example.umc10th_m4.global.security.JwtUtil;
import org.example.umc10th_m4.global.status.SuccessStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberController {

    private final MemberService memberService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @PostMapping("/signup")
    public ApiResponse<MemberResponseDto> signUp(@RequestBody @Valid MemberSignupRequestDto request) {
        return ApiResponse.onSuccess(SuccessStatus.CREATED, memberService.signUp(request));
    }

    @PostMapping("/login")
    public ApiResponse<MemberResponseDto> login(@RequestBody MemberLoginRequestDto request) {
        // 이메일 + 비밀번호 검증
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        // 인증 성공 → AuthMember에서 JWT 토큰 생성
        AuthMember authMember = (AuthMember) authentication.getPrincipal();
        String accessToken = jwtUtil.createAccessToken(authMember);

        // 회원 정보 조회 후 토큰 포함해서 응답
        MemberResponseDto memberInfo = memberService.getMember(authMember.getMemberId());
        return ApiResponse.onSuccess(MemberResponseDto.builder()
                .memberId(memberInfo.getMemberId())
                .name(memberInfo.getName())
                .email(memberInfo.getEmail())
                .point(memberInfo.getPoint())
                .token(accessToken)
                .build());
    }

    // 마이페이지 v2: JWT 토큰으로 본인 정보 조회
    @GetMapping("/me")
    public ApiResponse<MemberResponseDto> getMyProfile(@AuthenticationPrincipal AuthMember authMember) {
        return ApiResponse.onSuccess(memberService.getMember(authMember.getMemberId()));
    }

    @GetMapping("/{member_id}")
    public ApiResponse<MemberResponseDto> getProfile(@PathVariable(name = "member_id") long memberId) {
        return ApiResponse.onSuccess(memberService.getMember(memberId));
    }

    @DeleteMapping("/{member_id}/deletion")
    public ApiResponse<String> deleteMember(@PathVariable(name = "member_id") long memberId) {
        return ApiResponse.onSuccess("회원 탈퇴 완료");
    }
}
