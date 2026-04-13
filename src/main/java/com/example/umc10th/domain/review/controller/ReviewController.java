package com.example.umc10th.domain.review.controller;


import com.example.umc10th.domain.review.exception.code.ReviewSuccessCode;
import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/stores")
public class ReviewController {

    // 리뷰 작성
    @PostMapping("/{storeId}/reviews")
    public ApiResponse<ReviewResDTO.CreateReviewRes> createReview(
            @PathVariable Long storeId,
            @RequestBody ReviewReqDTO.CreateReviewReq request
    ) {
        return ApiResponse.onSuccess(ReviewSuccessCode.REVIEW_CREATE_OK, null);
    }
}