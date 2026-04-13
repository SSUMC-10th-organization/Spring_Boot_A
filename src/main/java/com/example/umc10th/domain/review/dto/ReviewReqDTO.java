package com.example.demo.review.dto;

public class ReviewReqDTO {

    public record ReviewCreate(
            Long storeId,
            Float score,
            String body
    ) {}
}
