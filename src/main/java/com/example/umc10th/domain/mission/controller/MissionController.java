package com.example.demo.mission.controller;

import com.example.demo.global.apiPayload.ApiResponse;
import com.example.demo.global.apiPayload.code.SuccessStatus;
import com.example.demo.mission.dto.MissionResDTO;
import com.example.demo.mission.service.MissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MissionController {

    private final MissionService missionService;

    @GetMapping("/me/missions")
    public ApiResponse<MissionResDTO.MissionListResult> getMissionList() {
        return ApiResponse.onSuccess(SuccessStatus._OK, missionService.getMissionList());
    }

    @PatchMapping("/me/missions/{missionId}")
    public ApiResponse<MissionResDTO.MissionCompleteResult> completeMission(
            @PathVariable Long missionId
    ) {
        return ApiResponse.onSuccess(SuccessStatus._OK, missionService.completeMission(missionId));
    }
}
