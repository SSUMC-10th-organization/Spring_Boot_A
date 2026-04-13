package com.example.demo.review.controller;

import com.example.demo.global.apiPayload.ApiResponse;
import com.example.demo.global.apiPayload.code.SuccessStatus;
import com.example.demo.review.dto.ReviewReqDTO;
import com.example.demo.review.dto.ReviewResDTO;
import com.example.demo.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/me/reviews")
    public ApiResponse<ReviewResDTO.ReviewCreateResult> createReview(
            @RequestBody ReviewReqDTO.ReviewCreate request
    ) {
        return ApiResponse.onSuccess(SuccessStatus._OK, reviewService.createReview(request));
    }
}
