package org.example.umc10th_m4.domain.mission.controller;

import lombok.RequiredArgsConstructor;
import org.example.umc10th_m4.domain.mission.dto.MissionRequestDto;
import org.example.umc10th_m4.domain.mission.dto.MissionResponseDto;
import org.example.umc10th_m4.domain.mission.service.MissionService;
import org.example.umc10th_m4.global.common.ApiResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/missions")
public class MissionController {

    private final MissionService missionService;

    // 홈 화면 - 현재 선택된 지역에서 도전 가능한 미션 목록 (페이징)
    @GetMapping("/region")
    public ApiResponse<List<MissionResponseDto>> getMissionsByRegion(
            @RequestParam(name = "region") String region,
            @RequestParam(name = "page", required = false, defaultValue = "1") int page) {
        return ApiResponse.onSuccess(missionService.getMissionsByRegion(region, page));
    }

//    @GetMapping("/{mission_id}")
//    public ApiResponse<MissionResponseDto> getMissionDetail(@PathVariable(name = "mission_id") long missionId) {
//        return ApiResponse.onSuccess(null);
//    }

    // 내가 진행중/완료한 미션 모아보기 (페이징)
    @GetMapping("/mine")
    public ApiResponse<List<MissionResponseDto>> getMyMissions(
            @RequestParam(name = "member_id") long memberId,
            @RequestParam(name = "status") String status,
            @RequestParam(name = "page", required = false, defaultValue = "1") int page) {
        return ApiResponse.onSuccess(missionService.getMyMissions(memberId, status, page));
    }

//    @GetMapping("/{mission_id}/progress")
//    public ApiResponse<MissionResponseDto> getMissionProgress(@PathVariable(name = "mission_id") long missionId) {
//        return ApiResponse.onSuccess(null);
//    }
//
//    @PostMapping("/{mission_id}")
//    public ApiResponse<MissionResponseDto> performMission(
//            @PathVariable(name = "mission_id") long missionId,
//            @RequestBody MissionRequestDto request) {
//        return ApiResponse.onSuccess(null);
//    }
}
