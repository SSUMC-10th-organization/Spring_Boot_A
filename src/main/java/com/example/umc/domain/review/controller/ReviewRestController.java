package com.example.umc.domain.review.controller;

import com.example.umc.domain.review.code.ReviewSuccessCode;
import com.example.umc.domain.review.dto.ReviewRequestDTO;
import com.example.umc.domain.review.dto.ReviewResponseDTO;
import com.example.umc.domain.review.service.ReviewService;
import com.example.umc.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Review", description = "리뷰 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/reviews")
public class ReviewRestController {

    private final ReviewService reviewService;

    @Operation(summary = "리뷰 작성 API", description = "사진 업로드는 제외하고 평점과 내용만 저장합니다.")
    @PostMapping
    public ApiResponse<ReviewResponseDTO.CreateReviewResponse> createReview(
            @RequestBody @Valid ReviewRequestDTO.CreateReviewRequest request
    ) {
        ReviewResponseDTO.CreateReviewResponse response = reviewService.createReview(request);
        return ApiResponse.of(ReviewSuccessCode.REVIEW_CREATED, response);
    }

    @Operation(summary = "가게 리뷰 목록 조회 API", description = "점포관리 리뷰 탭에 필요한 리뷰 목록을 페이징으로 조회합니다.")
    @GetMapping("/stores/{storeId}")
    public ApiResponse<ReviewResponseDTO.StoreReviewPreviewListResponse> getStoreReviews(
            @PathVariable Long storeId,
            @RequestParam(defaultValue = "0") Integer page
    ) {
        ReviewResponseDTO.StoreReviewPreviewListResponse response = reviewService.getStoreReviews(storeId, page);
        return ApiResponse.of(ReviewSuccessCode.STORE_REVIEWS_FOUND, response);
    }
}
