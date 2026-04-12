package org.example.umc10th_m4.domain.review.controller;

import lombok.RequiredArgsConstructor;
import org.example.umc10th_m4.domain.review.dto.ReviewRequestDto;
import org.example.umc10th_m4.domain.review.dto.ReviewResponseDto;
import org.example.umc10th_m4.global.common.ApiResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ReviewController {

    @PostMapping("/stores/{store_id}/reviews")
    public ApiResponse<ReviewResponseDto> addReview(
            @PathVariable(name = "store_id") long storeId,
            @RequestBody ReviewRequestDto request) {
        return ApiResponse.onSuccess(null);
    }

    @GetMapping("/stores/{store_id}/reviews")
    public ApiResponse<List<ReviewResponseDto>> getReviews(
            @PathVariable(name = "store_id") long storeId,
            @RequestParam(name = "page", required = false, defaultValue = "1") int page) {
        return ApiResponse.onSuccess(null);
    }

    @PatchMapping("/reviews/{review_id}")
    public ApiResponse<ReviewResponseDto> updateReview(
            @PathVariable(name = "review_id") long reviewId,
            @RequestBody ReviewRequestDto request) {
        return ApiResponse.onSuccess(null);
    }

    @DeleteMapping("/reviews/{review_id}")
    public ApiResponse<String> deleteReview(@PathVariable(name = "review_id") long reviewId) {
        return ApiResponse.onSuccess(null);
    }
}