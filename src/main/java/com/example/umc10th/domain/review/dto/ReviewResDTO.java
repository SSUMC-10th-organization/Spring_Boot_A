package com.example.demo.review.dto;

import java.time.LocalDateTime;

public class ReviewResDTO {

    public record ReviewCreateResult(
            Long reviewId,
            LocalDateTime createdAt
    ) {}
}
