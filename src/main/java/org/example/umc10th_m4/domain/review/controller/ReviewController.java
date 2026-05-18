package org.example.umc10th_m4.domain.review.controller;

import lombok.RequiredArgsConstructor;
import org.example.umc10th_m4.domain.review.dto.ReviewRequestDto;
import org.example.umc10th_m4.domain.review.dto.ReviewResponseDto;
import org.example.umc10th_m4.domain.review.service.ReviewService;
import org.example.umc10th_m4.global.common.ApiResponse;
import org.example.umc10th_m4.global.status.SuccessStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ReviewController {

    private final ReviewService reviewService;

    // 리뷰 작성 쿼리
    @PostMapping("/stores/{storeId}/reviews")
    public ApiResponse<ReviewResponseDto> addReview(
            @PathVariable(name = "storeId") long storeId,
            @RequestBody ReviewRequestDto request) {
        return ApiResponse.onSuccess(SuccessStatus.CREATED, reviewService.addReview(storeId, request));
    }

    @GetMapping("/stores/{storeId}/reviews")
    public ApiResponse<List<ReviewResponseDto>> getReviews(
            @PathVariable(name = "storeId") long storeId,
            @RequestParam(name = "page", required = false, defaultValue = "1") int page) {
        return ApiResponse.onSuccess(reviewService.getReviews(storeId, page));
    }

//    @PatchMapping("/reviews/{reviewId}")
//    public ApiResponse<ReviewResponseDto> updateReview(
//            @PathVariable(name = "reviewId") long reviewId,
//            @RequestBody ReviewRequestDto request) {
//        return ApiResponse.onSuccess(null);
//    }
//
//    @DeleteMapping("/reviews/{reviewId}")
//    public ApiResponse<String> deleteReview(@PathVariable(name = "reviewId") long reviewId) {
//        return ApiResponse.onSuccess(null);
//    }
}
