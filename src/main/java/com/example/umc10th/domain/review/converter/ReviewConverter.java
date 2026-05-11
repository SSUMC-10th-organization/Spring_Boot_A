package com.example.umc10th.domain.review.converter;

import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.entity.Review;

import java.util.List;

public class ReviewConverter {

    public static ReviewResDTO.ReviewItemRes toReviewItemRes(Review review) {
        return ReviewResDTO.ReviewItemRes.builder()
                .reviewId(review.getId())
                .score(review.getRating() != null ? review.getRating().floatValue() : null)
                .body(review.getBody())
                .createdAt(review.getCreatedAt() != null ? review.getCreatedAt().toLocalDate() : null)
                .build();
    }

    public static <T> ReviewResDTO.CursorPaginationRes<T> toPagination(
            List<T> data, Boolean hasNext, String nextCursor, Integer pageSize) {
        return ReviewResDTO.CursorPaginationRes.<T>builder()
                .data(data)
                .hasNext(hasNext)
                .nextCursor(nextCursor)
                .pageSize(pageSize)
                .build();
    }
}
