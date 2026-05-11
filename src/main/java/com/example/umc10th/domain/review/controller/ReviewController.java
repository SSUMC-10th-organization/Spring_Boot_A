package com.example.umc10th.domain.review.controller;

import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.service.ReviewService;
import com.example.umc10th.global.handler.ApiResponse;
import com.example.umc10th.global.handler.SuccessStatus;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class ReviewController {

    private final ReviewService reviewService;

    // 리뷰 생성
    @PostMapping("/me/reviews")
    public ApiResponse<ReviewResDTO.ReviewCreateResult> createReview(
            @RequestParam Long memberId,
            @RequestBody @Valid ReviewReqDTO.ReviewCreate request
    ) {
        return ApiResponse.onSuccess(
                SuccessStatus._OK,
                reviewService.createReview(memberId, request)
        );
    }

    // 내가 생성한 리뷰 조회 (커서 기반)
    // query = "id" or "star"
    @GetMapping("/me/reviews")
    public ApiResponse<ReviewResDTO.ReviewListResult> getMyReviews(
            @RequestParam Long memberId,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "-1") String cursor,
            @RequestParam(defaultValue = "id") String query
    ) {
        return ApiResponse.onSuccess(
                SuccessStatus._OK,
                reviewService.getMyReviews(memberId, pageSize, cursor, query)
        );
    }
}