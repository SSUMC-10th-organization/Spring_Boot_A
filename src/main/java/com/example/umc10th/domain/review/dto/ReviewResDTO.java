package com.example.umc10th.domain.review.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

public class ReviewResDTO {

    @Getter
    @Builder
    @AllArgsConstructor
    public static class CreateReviewRes {
        private Long reviewId;
        private String authorNickname;
        private Integer rating;
        private String content;
        private LocalDateTime createdAt;
    }
}
