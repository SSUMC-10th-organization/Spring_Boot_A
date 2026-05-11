package com.example.umc10th.domain.user.controller;

import com.example.umc10th.domain.mission.enums.MissionStatus;
import com.example.umc10th.domain.user.dto.UserReqDTO;
import com.example.umc10th.domain.user.dto.UserResDTO;
import com.example.umc10th.domain.user.exception.code.UserSuccessCode;
import com.example.umc10th.domain.user.service.UserService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "사용자", description = "사용자 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Operation(summary = "마이페이지 조회", description = "내 닉네임, 이메일, 전화번호, 포인트를 조회합니다.")
    @GetMapping("/me")
    public ApiResponse<UserResDTO.MyPageRes> getMyPage(
            @Parameter(description = "유저 ID") @RequestParam Long userId
    ) {
        UserResDTO.MyPageRes result = userService.getMyPage(userId);
        return ApiResponse.onSuccess(UserSuccessCode.USER_MYPAGE_OK, result);
    }

    @Operation(summary = "미션 진행 현황 조회")
    @GetMapping("/me/mission-progress")
    public ApiResponse<UserResDTO.MissionProgressRes> getMissionProgress() {
        return ApiResponse.onSuccess(UserSuccessCode.USER_MISSION_PROGRESS_OK, null);
    }

    @Operation(summary = "미션 도전하기")
    @PostMapping("/me/missions/{missionId}/challenge")
    public ApiResponse<UserResDTO.MissionChallengeRes> challengeMission(
            @PathVariable Long missionId
    ) {
        return ApiResponse.onSuccess(UserSuccessCode.USER_MISSION_CHALLENGE_OK, null);
    }

    @Operation(summary = "미션 성공 처리")
    @PatchMapping("/me/missions/{userMissionId}")
    public ApiResponse<UserResDTO.MissionChallengeRes> successMission(
            @PathVariable Long userMissionId,
            @Valid @RequestBody UserReqDTO.MissionSuccessReq request
    ) {
        return ApiResponse.onSuccess(UserSuccessCode.USER_MISSION_SUCCESS_OK, null);
    }
}
