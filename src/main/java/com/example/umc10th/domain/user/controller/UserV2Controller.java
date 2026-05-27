package com.example.umc10th.domain.user.controller;

import com.example.umc10th.domain.user.dto.UserResDTO;
import com.example.umc10th.domain.user.exception.code.UserSuccessCode;
import com.example.umc10th.domain.user.service.UserService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.security.AuthMember;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "사용자 v2", description = "사용자 관련 API v2 (JWT 인증 필요)")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/users")
public class UserV2Controller {

    private final UserService userService;

    @Operation(summary = "마이페이지 조회 v2", description = "JWT 토큰으로 인증 후 내 정보를 조회합니다.")
    @GetMapping("/me")
    public ApiResponse<UserResDTO.MyPageRes> getMyPageV2(
            @AuthenticationPrincipal AuthMember authMember
    ) {
        UserResDTO.MyPageRes result = userService.getMyPage(authMember.getUser().getId());
        return ApiResponse.onSuccess(UserSuccessCode.USER_MYPAGE_OK, result);
    }
}
