package com.example.umc.domain.review.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

public class ReviewResponseDTO {

    private ReviewResponseDTO() {
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateReviewResponse {
        private Long reviewId;
        private Long userMissionId;
        private Integer rating;
        private LocalDateTime createdAt;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class StoreReviewPreviewListResponse {
        private List<StoreReviewPreviewResponse> reviews;
        private Integer listSize;
        private Integer totalPage;
        private Long totalElements;
        private Boolean isFirst;
        private Boolean isLast;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class StoreReviewPreviewResponse {
        private Long reviewId;
        private String nickname;
        private Integer rating;
        private String content;
        private LocalDateTime createdAt;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MyReviewCursorListResponse {
        private List<MyReviewPreviewResponse> reviews;
        private Integer listSize;
        private Boolean hasNext;
        private String nextCursor;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MyReviewPreviewResponse {
        private Long reviewId;
        private String storeName;
        private String missionTitle;
        private Integer rating;
        private String content;
        private LocalDateTime createdAt;
    }
}
