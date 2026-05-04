package org.example.umc10th_m4.domain.review.service;

import org.example.umc10th_m4.domain.review.dto.ReviewRequestDto;
import org.example.umc10th_m4.domain.review.dto.ReviewResponseDto;

import java.util.List;

public interface ReviewService {
    ReviewResponseDto addReview(long storeId, ReviewRequestDto request);
    List<ReviewResponseDto> getReviews(long storeId, int page);
}
