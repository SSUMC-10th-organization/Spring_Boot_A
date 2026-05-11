package com.example.umc10th.domain.review.service;

import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;

public interface ReviewService {
    ReviewResDTO.CreateReviewRes createReview(Long userId, Long restaurantId, ReviewReqDTO.CreateReviewReq request);
    ReviewResDTO.CursorPaginationRes<ReviewResDTO.ReviewItemRes> getReviews(
            Long memberId, Integer pageSize, String cursor, String query);
}
