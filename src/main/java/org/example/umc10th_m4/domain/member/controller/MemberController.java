package org.example.umc10th_m4.domain.member.controller;

import lombok.RequiredArgsConstructor;
import org.example.umc10th_m4.domain.member.dto.*;
import org.example.umc10th_m4.domain.member.service.MemberService;
import org.example.umc10th_m4.global.common.ApiResponse;
import org.example.umc10th_m4.global.status.SuccessStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public ApiResponse<MemberResponseDto> signUp(@RequestBody MemberSignupRequestDto request) {
        return ApiResponse.onSuccess(SuccessStatus.CREATED, null);
    }

    @PostMapping("/login")
    public ApiResponse<MemberResponseDto> login(@RequestBody MemberLoginRequestDto request) {
        return ApiResponse.onSuccess(null);
    }

    // 마이 페이지 화면 쿼리
    @GetMapping("/{member_id}")
    public ApiResponse<MemberResponseDto> getProfile(@PathVariable(name = "member_id") long memberId) {
        return ApiResponse.onSuccess(memberService.getMember(memberId));
    }

    @DeleteMapping("/{member_id}/deletion")
    public ApiResponse<String> deleteMember(@PathVariable(name = "member_id") long memberId) {
        return ApiResponse.onSuccess("회원 탈퇴 완료");
    }

    @GetMapping("/{member_id}/alarms")
    public ApiResponse<List<MemberAlarmResponseDto>> getAlarms(
            @PathVariable(name = "member_id") long memberId,
            @RequestParam(name = "unread") boolean unread) {
        return ApiResponse.onSuccess(null);
    }

    @PatchMapping("/alarms/{alarm_id}/read")
    public ApiResponse<MemberAlarmResponseDto> readAlarm(@PathVariable(name = "alarm_id") long alarmId) {
        return ApiResponse.onSuccess(null);
    }

    @DeleteMapping("/alarms/{alarm_id}")
    public ApiResponse<String> deleteAlarm(@PathVariable(name = "alarm_id") long alarmId) {
        return ApiResponse.onSuccess("알람 삭제 완료");
    }

    //질의응답
    @PostMapping("/{member_id}/questions")
    public ApiResponse<MemberResponseDto> addQuestion(
            @PathVariable(name = "member_id") long memberId,
            @RequestBody MemberQuestionRequestDto request) {
        return ApiResponse.onSuccess(null);
    }

    @GetMapping("/{member_id}/questions")
    public ApiResponse<List<MemberResponseDto>> getQuestions(@PathVariable(name = "member_id") long memberId) {
        return ApiResponse.onSuccess(null);
    }
}
