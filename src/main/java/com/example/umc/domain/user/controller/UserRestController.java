package com.example.umc.domain.user.controller;

import com.example.umc.domain.user.code.UserSuccessCode;
import com.example.umc.domain.user.dto.UserRequestDTO;
import com.example.umc.domain.user.dto.UserResponseDTO;
import com.example.umc.domain.user.service.UserService;
import com.example.umc.domain.userMission.entity.UserMissionStatus;
import com.example.umc.global.apiPayload.ApiResponse;
import com.example.umc.global.security.CustomUserDetails;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "User", description = "유저 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserRestController {

    private final UserService userService;

    @Operation(summary = "마이페이지 조회 API", description = "Authorization 헤더의 Bearer 토큰으로 현재 로그인한 유저 정보를 조회합니다.")
    @RequestMapping(value = "/me", method = {RequestMethod.GET, RequestMethod.POST})
    public ApiResponse<UserResponseDTO.MyPageResponse> getMyPage(
            @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        UserResponseDTO.MyPageResponse response = userService.getMyPage(userDetails.getId());
        return ApiResponse.of(UserSuccessCode.USER_FOUND, response);
    }

    @Operation(summary = "내 미션 목록 조회 API", description = "진행중/진행완료 미션을 페이징으로 조회합니다.")
    @GetMapping("/{userId}/missions")
    public ApiResponse<UserResponseDTO.UserMissionPreviewListResponse> getMyMissions(
            @PathVariable Long userId,
            @RequestParam UserMissionStatus status,
            @RequestParam(defaultValue = "0") Integer page
    ) {
        UserResponseDTO.UserMissionPreviewListResponse response = userService.getMyMissions(userId, status, page);
        return ApiResponse.of(UserSuccessCode.USER_MISSIONS_FOUND, response);
    }

    @Operation(summary = "내가 진행중인 미션 조회 API", description = "Request Body의 유저 ID로 진행중인 미션을 오프셋 기반 페이지네이션으로 조회합니다.")
    @PostMapping("/missions/in-progress")
    public ApiResponse<UserResponseDTO.UserMissionPreviewListResponse> getMyInProgressMissions(
            @RequestBody @Valid UserRequestDTO.MyInProgressMissionRequest request
    ) {
        UserResponseDTO.UserMissionPreviewListResponse response = userService.getMyInProgressMissions(request);
        return ApiResponse.of(UserSuccessCode.IN_PROGRESS_MISSIONS_FOUND, response);
    }
}
