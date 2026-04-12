package org.example.umc10th_m4.domain.mission.controller;

import lombok.RequiredArgsConstructor;
import org.example.umc10th_m4.domain.mission.dto.MissionRequestDto;
import org.example.umc10th_m4.domain.mission.dto.MissionResponseDto;
import org.example.umc10th_m4.global.common.ApiResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/missions")
public class MissionController {

    @GetMapping("/region")
    public ApiResponse<List<MissionResponseDto>> getMissionsByRegion(@RequestParam(name = "region") String region) {
        return ApiResponse.onSuccess(null);
    }

    @GetMapping("/{mission_id}")
    public ApiResponse<MissionResponseDto> getMissionDetail(@PathVariable(name = "mission_id") long missionId) {
        return ApiResponse.onSuccess(null);
    }

    @GetMapping("/mine")
    public ApiResponse<List<MissionResponseDto>> getMyMissions(@RequestParam(name = "status") String status) {
        return ApiResponse.onSuccess(null);
    }

    @GetMapping("/{mission_id}/progress")
    public ApiResponse<MissionResponseDto> getMissionProgress(@PathVariable(name = "mission_id") long missionId) {
        return ApiResponse.onSuccess(null);
    }

    @PostMapping("/{mission_id}")
    public ApiResponse<MissionResponseDto> performMission(
            @PathVariable(name = "mission_id") long missionId,
            @RequestBody MissionRequestDto request) {
        return ApiResponse.onSuccess(null);
    }
}