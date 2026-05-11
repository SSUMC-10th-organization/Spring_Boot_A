package com.example.umc10th.domain.mission.controller;

import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.enums.MissionStatus;
import com.example.umc10th.domain.mission.service.MissionService;
import com.example.umc10th.global.handler.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.example.umc10th.global.handler.SuccessStatus;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MissionController {

    private final MissionService missionService;

    // 내 미션 목록 조회
    @GetMapping("/me/missions")
    public ApiResponse<MissionResDTO.MissionListResult> getMissionList(
            @RequestParam Long memberId,
            @RequestParam MissionStatus status,
            @RequestParam(defaultValue = "0") int page
    ) {
        return ApiResponse.onSuccess(
                SuccessStatus._OK,
                missionService.getMissionList(memberId, status, page)
        );
    }

    @PatchMapping("/me/missions/{missionId}")
    public ApiResponse<MissionResDTO.MissionCompleteResult> completeMission(
            @PathVariable Long missionId
    ) {
        return ApiResponse.onSuccess(SuccessStatus._OK, missionService.completeMission(missionId));
    }

    @GetMapping("/home/missions")
    public ApiResponse<MissionResDTO.MissionListResult> getHomeMissions(
            @RequestParam Long locationId,
            @RequestParam(defaultValue = "0") int page
    ) {
        return ApiResponse.onSuccess(SuccessStatus._OK, missionService.getHomeMissions(locationId, page));
    }
}