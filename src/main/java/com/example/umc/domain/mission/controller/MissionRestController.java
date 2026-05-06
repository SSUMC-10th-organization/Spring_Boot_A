package com.example.umc.domain.mission.controller;

import com.example.umc.domain.mission.code.MissionSuccessCode;
import com.example.umc.domain.mission.dto.MissionResponseDTO;
import com.example.umc.domain.mission.service.MissionService;
import com.example.umc.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Mission", description = "미션 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/missions")
public class MissionRestController {

    private final MissionService missionService;

    @Operation(summary = "지역별 도전 가능 미션 조회 API", description = "선택한 지역 주소를 포함하는 미션을 페이징으로 조회합니다.")
    @GetMapping("/available")
    public ApiResponse<MissionResponseDTO.MissionPreviewListResponse> getAvailableMissions(
            @RequestParam String region,
            @RequestParam(defaultValue = "0") Integer page
    ) {
        MissionResponseDTO.MissionPreviewListResponse response = missionService.getAvailableMissions(region, page);
        return ApiResponse.of(MissionSuccessCode.AVAILABLE_MISSIONS_FOUND, response);
    }
}
