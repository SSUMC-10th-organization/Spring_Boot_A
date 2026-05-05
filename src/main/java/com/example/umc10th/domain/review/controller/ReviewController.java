package com.example.umc10th.domain.review.controller;

import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.service.ReviewService;
import com.example.umc10th.global.handler.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.example.umc10th.global.handler.SuccessStatus;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/me/reviews")
    public ApiResponse<ReviewResDTO.ReviewCreateResult> createReview(
            @RequestParam Long memberId,
            @RequestBody ReviewReqDTO.ReviewCreate request
    ) {
        return ApiResponse.onSuccess(SuccessStatus._OK, reviewService.createReview(memberId, request));
    }
}