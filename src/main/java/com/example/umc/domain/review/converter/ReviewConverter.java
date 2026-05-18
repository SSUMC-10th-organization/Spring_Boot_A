package com.example.umc.domain.review.converter;

import com.example.umc.domain.review.dto.ReviewRequestDTO;
import com.example.umc.domain.review.dto.ReviewResponseDTO;
import com.example.umc.domain.review.entity.Review;
import com.example.umc.domain.userMission.entity.UserMission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;

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

    public static ReviewResponseDTO.MyReviewCursorListResponse toMyReviewCursorListResponse(
            Slice<Review> reviews,
            String sortBy
    ) {
        List<ReviewResponseDTO.MyReviewPreviewResponse> reviewList = reviews.stream()
                .map(ReviewConverter::toMyReviewPreviewResponse)
                .toList();

        return ReviewResponseDTO.MyReviewCursorListResponse.builder()
                .reviews(reviewList)
                .listSize(reviewList.size())
                .hasNext(reviews.hasNext())
                .nextCursor(createNextCursor(reviews, sortBy))
                .build();
    }

    private static ReviewResponseDTO.MyReviewPreviewResponse toMyReviewPreviewResponse(Review review) {
        return ReviewResponseDTO.MyReviewPreviewResponse.builder()
                .reviewId(review.getId())
                .storeName(review.getUserMission().getMission().getStore().getName())
                .missionTitle(review.getUserMission().getMission().getTitle())
                .rating(review.getRating())
                .content(review.getContent())
                .createdAt(review.getCreatedAt())
                .build();
    }

    private static String createNextCursor(Slice<Review> reviews, String sortBy) {
        if (!reviews.hasNext() || reviews.isEmpty()) {
            return null;
        }

        Review lastReview = reviews.getContent().get(reviews.getNumberOfElements() - 1);
        if ("RATING".equals(sortBy)) {
            return "RATING:%d:%d".formatted(lastReview.getRating(), lastReview.getId());
        }
        return "ID:%d".formatted(lastReview.getId());
    }
}
