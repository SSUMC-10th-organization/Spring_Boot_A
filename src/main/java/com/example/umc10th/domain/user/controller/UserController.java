package com.example.umc10th.domain.user.controller;

import com.example.umc10th.domain.mission.enums.MissionStatus;
import com.example.umc10th.domain.user.dto.UserReqDTO;
import com.example.umc10th.domain.user.dto.UserResDTO;
import com.example.umc10th.domain.user.dto.UserResDTO;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users/me")
public class UserController {

    // 사용자 정보 및 포인트 조회
    @GetMapping
    public ApiResponse<UserResDTO.UserInfoRes> getUserInfo() {
        return ApiResponse.onSuccess(GeneralSuccessCode.USER_INFO_OK, null);
    }

    // 미션 진행 현황 조회
    @GetMapping("/mission-progress")
    public ApiResponse<UserResDTO.MissionProgressRes> getMissionProgress() {
        return ApiResponse.onSuccess(GeneralSuccessCode.USER_MISSION_PROGRESS_OK, null);
    }

    // 내가 받은 미션 목록 조회 + 미션 목록 조회 (status 필터)
    @GetMapping("/missions")
    public ApiResponse<List<UserResDTO.MissionRes>> getMyMissions(
            @RequestParam(required = false) MissionStatus status
    ) {
        return ApiResponse.onSuccess(GeneralSuccessCode.USER_MISSIONS_OK, null);
    }

    // 미션 도전하기
    @PostMapping("/missions/{missionId}/challenge")
    public ApiResponse<UserResDTO.MissionChallengeRes> challengeMission(
            @PathVariable Long missionId
    ) {
        return ApiResponse.onSuccess(GeneralSuccessCode.USER_MISSION_CHALLENGE_OK, null);
    }

    // 미션 성공 누르기
    @PatchMapping("/missions/{userMissionId}")
    public ApiResponse<UserResDTO.MissionChallengeRes> successMission(
            @PathVariable Long userMissionId,
            @RequestBody UserReqDTO.MissionSuccessReq request
    ) {
        return ApiResponse.onSuccess(GeneralSuccessCode.USER_MISSION_SUCCESS_OK, null);
    }
}