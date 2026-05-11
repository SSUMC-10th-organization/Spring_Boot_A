package com.example.umc10th.domain.review.controller;

import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.exception.code.ReviewSuccessCode;
import com.example.umc10th.domain.review.service.ReviewService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "리뷰", description = "리뷰 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/stores")
public class ReviewController {

    private final ReviewService reviewService;

    @Operation(summary = "리뷰 작성", description = "특정 가게에 리뷰를 작성합니다.")
    @PostMapping("/{storeId}/reviews")
    public ApiResponse<ReviewResDTO.CreateReviewRes> createReview(
            @Parameter(description = "가게 ID") @PathVariable Long storeId,
            @Parameter(description = "작성자 유저 ID") @RequestParam Long userId,
            @Valid @RequestBody ReviewReqDTO.CreateReviewReq request
    ) {
        ReviewResDTO.CreateReviewRes result = reviewService.createReview(userId, storeId, request);
        return ApiResponse.onSuccess(ReviewSuccessCode.REVIEW_CREATE_OK, result);
    }
}
