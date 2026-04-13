package com.example.umc10th.domain.review.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class ReviewResDTO {

    @Getter
    @AllArgsConstructor
    public static class CreateReviewRes {
        private Long reviewId;
        private Long storeId;
        private Integer rating;
        private String content;
    }
}