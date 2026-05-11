package com.example.umc10th.domain.user.controller;

import com.example.umc10th.domain.mission.dto.MemberMissionResDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.exception.code.MissionSuccessCode;
import com.example.umc10th.domain.mission.service.MissionService;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.exception.code.ReviewSuccessCode;
import com.example.umc10th.domain.review.service.ReviewService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "멤버", description = "멤버 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/members")
@Validated
public class MemberController {

    private final MissionService missionService;
    private final ReviewService reviewService;

    @Operation(
            summary = "진행중인 미션 목록 조회",
            description = "특정 멤버의 진행중인 미션 목록을 오프셋 기반 페이지네이션으로 조회합니다."
    )
    @GetMapping("/{memberId}/missions")
    public ApiResponse<MissionResDTO.OffsetPaginationRes<MemberMissionResDTO.OngoingMissionItem>> getMissions(
            @Parameter(description = "멤버 ID") @PathVariable Long memberId,
            @Parameter(description = "페이지당 항목 수") @RequestParam(defaultValue = "10") @Min(1) Integer pageSize,
            @Parameter(description = "페이지 번호 (0부터 시작)") @RequestParam(defaultValue = "0") @Min(0) Integer pageNumber,
            @Parameter(description = "정렬 기준 필드 (선택, 없으면 id DESC)") @RequestParam(required = false) String sort
    ) {
        MissionResDTO.OffsetPaginationRes<MemberMissionResDTO.OngoingMissionItem> result =
                missionService.getMissions(memberId, pageSize, pageNumber, sort);
        return ApiResponse.onSuccess(MissionSuccessCode.ONGOING_MISSIONS_OK, result);
    }

    @Operation(
            summary = "내 리뷰 목록 조회",
            description = "특정 멤버가 작성한 리뷰 목록을 커서 기반 페이지네이션으로 조회합니다. query: id(ID순) 또는 star(별점순)"
    )
    @GetMapping("/{memberId}/reviews")
    public ApiResponse<ReviewResDTO.CursorPaginationRes<ReviewResDTO.ReviewItemRes>> getReviews(
            @Parameter(description = "멤버 ID") @PathVariable Long memberId,
            @Parameter(description = "페이지당 항목 수") @RequestParam(defaultValue = "10") @Min(1) Integer pageSize,
            @Parameter(description = "커서 값 (첫 페이지는 -1)") @RequestParam(defaultValue = "-1") String cursor,
            @Parameter(description = "정렬 기준 (id 또는 star)") @RequestParam(defaultValue = "id") String query
    ) {
        ReviewResDTO.CursorPaginationRes<ReviewResDTO.ReviewItemRes> result =
                reviewService.getReviews(memberId, pageSize, cursor, query);
        return ApiResponse.onSuccess(ReviewSuccessCode.REVIEWS_OK, result);
    }
}
