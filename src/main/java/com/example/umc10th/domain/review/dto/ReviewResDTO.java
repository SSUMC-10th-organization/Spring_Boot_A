package com.example.umc10th.domain.review.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

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

    @Getter
    @Builder
    @AllArgsConstructor
    public static class ReviewItemRes {
        private Long reviewId;
        private Float score;
        private String body;
        private LocalDate createdAt;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class CursorPaginationRes<T> {
        private List<T> data;
        private Boolean hasNext;
        private String nextCursor;
        private Integer pageSize;
    }
}
