package com.example.umc.domain.review.converter;

import com.example.umc.domain.review.dto.ReviewRequestDTO;
import com.example.umc.domain.review.dto.ReviewResponseDTO;
import com.example.umc.domain.review.entity.Review;
import com.example.umc.domain.userMission.entity.UserMission;
import org.springframework.data.domain.Page;

import java.util.List;

public class ReviewConverter {

    private ReviewConverter() {
    }

    public static Review toReview(ReviewRequestDTO.CreateReviewRequest request, UserMission userMission) {
        return Review.builder()
                .userMission(userMission)
                .rating(request.getRating())
                .content(request.getContent())
                .build();
    }

    public static ReviewResponseDTO.CreateReviewResponse toCreateReviewResponse(Review review) {
        return ReviewResponseDTO.CreateReviewResponse.builder()
                .reviewId(review.getId())
                .userMissionId(review.getUserMission().getId())
                .rating(review.getRating())
                .createdAt(review.getCreatedAt())
                .build();
    }

    public static ReviewResponseDTO.StoreReviewPreviewListResponse toStoreReviewPreviewListResponse(
            Page<Review> reviews
    ) {
        List<ReviewResponseDTO.StoreReviewPreviewResponse> reviewList = reviews.stream()
                .map(ReviewConverter::toStoreReviewPreviewResponse)
                .toList();

        return ReviewResponseDTO.StoreReviewPreviewListResponse.builder()
                .reviews(reviewList)
                .listSize(reviewList.size())
                .totalPage(reviews.getTotalPages())
                .totalElements(reviews.getTotalElements())
                .isFirst(reviews.isFirst())
                .isLast(reviews.isLast())
                .build();
    }

    private static ReviewResponseDTO.StoreReviewPreviewResponse toStoreReviewPreviewResponse(Review review) {
        return ReviewResponseDTO.StoreReviewPreviewResponse.builder()
                .reviewId(review.getId())
                .nickname(review.getUserMission().getUser().getName())
                .rating(review.getRating())
                .content(review.getContent())
                .createdAt(review.getCreatedAt())
                .build();
    }
}
