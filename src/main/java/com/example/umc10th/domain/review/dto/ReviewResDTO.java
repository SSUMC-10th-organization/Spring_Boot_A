package com.example.umc10th.domain.review.dto;

import java.time.LocalDateTime;
import java.util.List;

public class ReviewResDTO {

    public record ReviewCreateResult(
            Long reviewId,
            LocalDateTime createdAt
    ) {}

    public record ReviewDetail(
            Long reviewId,
            String storeName,
            Float star,
            String content,
            LocalDateTime createdAt
    ) {}

    // 커서 기반 페이지네이션 응답
    public record ReviewListResult(
            List<ReviewDetail> reviewList,
            Boolean hasNext,
            String nextCursor,
            Integer pageSize
    ) {}
}