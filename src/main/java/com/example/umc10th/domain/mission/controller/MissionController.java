package com.example.umc10th.domain.mission.controller;

import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.enums.MissionStatus;
import com.example.umc10th.domain.mission.exception.code.MissionSuccessCode;
import com.example.umc10th.domain.mission.service.MissionService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "미션", description = "미션 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/missions")
public class MissionController {

    private final MissionService missionService;

    @Operation(
            summary = "내 미션 목록 조회 (페이징)",
            description = "진행중(ONGOING) 또는 완료(SUCCESS) 상태의 내 미션 목록을 페이징으로 조회합니다."
    )
    @GetMapping("/my")
    public ApiResponse<MissionResDTO.MyMissionListRes> getMyMissions(
            @Parameter(description = "유저 ID") @RequestParam Long userId,
            @Parameter(description = "미션 상태 (ONGOING / SUCCESS)") @RequestParam MissionStatus status,
            @Parameter(description = "페이지 번호 (0부터 시작)") @RequestParam(defaultValue = "0") int page
    ) {
        MissionResDTO.MyMissionListRes result = missionService.getMyMissions(userId, status, page);
        return ApiResponse.onSuccess(MissionSuccessCode.MY_MISSIONS_OK, result);
    }

    @Operation(
            summary = "홈 화면 - 도전 가능한 미션 목록 (페이징)",
            description = "현재 선택된 지역에서 아직 도전하지 않은 미션 목록을 페이징으로 조회합니다."
    )
    @GetMapping("/home")
    public ApiResponse<MissionResDTO.HomeMissionListRes> getHomeMissions(
            @Parameter(description = "유저 ID") @RequestParam Long userId,
            @Parameter(description = "지역 ID") @RequestParam Long locationId,
            @Parameter(description = "페이지 번호 (0부터 시작)") @RequestParam(defaultValue = "0") int page
    ) {
        MissionResDTO.HomeMissionListRes result = missionService.getHomeMissions(userId, locationId, page);
        return ApiResponse.onSuccess(MissionSuccessCode.HOME_MISSIONS_OK, result);
    }
}
