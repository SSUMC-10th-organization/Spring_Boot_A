package org.example.umc10th_m4.domain.review.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.umc10th_m4.domain.review.dto.ReviewRequestDto;
import org.example.umc10th_m4.domain.review.dto.ReviewResponseDto;
import org.example.umc10th_m4.domain.review.service.ReviewService;
import org.example.umc10th_m4.global.common.ApiResponse;
import org.example.umc10th_m4.global.common.CursorPageResponse;
import org.example.umc10th_m4.global.status.SuccessStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ReviewController {

    private final ReviewService reviewService;

    // 리뷰 작성
    @PostMapping("/stores/{store_id}/reviews")
    public ApiResponse<ReviewResponseDto> addReview(
            @PathVariable(name = "store_id") long storeId,
            @RequestBody @Valid ReviewRequestDto request) {
        return ApiResponse.onSuccess(SuccessStatus.CREATED, reviewService.addReview(storeId, request));
    }

    // 가게 리뷰 목록 조회 (오프셋)
    @GetMapping("/stores/{store_id}/reviews")
    public ApiResponse<List<ReviewResponseDto>> getReviews(
            @PathVariable(name = "store_id") long storeId,
            @RequestParam(name = "page", required = false, defaultValue = "1") int page) {
        return ApiResponse.onSuccess(reviewService.getReviews(storeId, page));
    }

    // 내가 작성한 리뷰 목록 조회 (커서 기반)
    // query=id  → ID 내림차순 정렬, cursor 형식: "id:{lastId}"
    // query=score → 별점 내림차순 정렬, cursor 형식: "score:{lastScore}:{lastId}"
    @GetMapping("/members/{member_id}/reviews")
    public ApiResponse<CursorPageResponse<ReviewResponseDto>> getMyReviews(
            @PathVariable(name = "member_id") long memberId,
            @RequestParam(required = false, defaultValue = "id") String query,
            @RequestParam(required = false) String cursor,
            @RequestParam(required = false, defaultValue = "10") int size) {
        return ApiResponse.onSuccess(reviewService.getMyReviews(memberId, query, cursor, size));
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
