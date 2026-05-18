package com.example.umc.domain.review.service;

import com.example.umc.domain.review.code.ReviewErrorCode;
import com.example.umc.domain.review.converter.ReviewConverter;
import com.example.umc.domain.review.dto.ReviewRequestDTO;
import com.example.umc.domain.review.dto.ReviewResponseDTO;
import com.example.umc.domain.review.entity.Review;
import com.example.umc.domain.review.exception.ReviewException;
import com.example.umc.domain.review.repository.ReviewRepository;
import com.example.umc.domain.user.repository.UserRepository;
import com.example.umc.domain.userMission.entity.UserMission;
import com.example.umc.domain.userMission.repository.UserMissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private static final String SORT_BY_ID = "ID";
    private static final String SORT_BY_RATING = "RATING";

    private final ReviewRepository reviewRepository;
    private final UserMissionRepository userMissionRepository;
    private final UserRepository userRepository;

    @Transactional
    public ReviewResponseDTO.CreateReviewResponse createReview(ReviewRequestDTO.CreateReviewRequest request) {
        if (reviewRepository.existsByUserMissionId(request.getUserMissionId())) {
            throw new ReviewException(ReviewErrorCode.REVIEW_ALREADY_EXISTS);
        }

        UserMission userMission = userMissionRepository.findById(request.getUserMissionId())
                .orElseThrow(() -> new ReviewException(ReviewErrorCode.USER_MISSION_NOT_FOUND));

        Review review = ReviewConverter.toReview(request, userMission);
        return ReviewConverter.toCreateReviewResponse(reviewRepository.save(review));
    }

    @Transactional(readOnly = true)
    public ReviewResponseDTO.StoreReviewPreviewListResponse getStoreReviews(Long storeId, Integer page) {
        Page<Review> reviews = reviewRepository.findReviewsByStoreId(storeId, PageRequest.of(page, 10));
        return ReviewConverter.toStoreReviewPreviewListResponse(reviews);
    }

    @Transactional(readOnly = true)
    public ReviewResponseDTO.MyReviewCursorListResponse getMyReviews(
            ReviewRequestDTO.MyReviewCursorRequest request
    ) {
        if (!userRepository.existsById(request.getUserId())) {
            throw new ReviewException(ReviewErrorCode.USER_NOT_FOUND);
        }

        Slice<Review> reviews = SORT_BY_RATING.equals(request.getSortBy())
                ? findMyReviewsByRating(request)
                : findMyReviewsById(request);

        return ReviewConverter.toMyReviewCursorListResponse(reviews, request.getSortBy());
    }

    private Slice<Review> findMyReviewsById(ReviewRequestDTO.MyReviewCursorRequest request) {
        Long cursorId = parseIdCursor(request.getCursor());
        PageRequest pageRequest = PageRequest.of(0, request.getSize());

        if (cursorId == null) {
            return reviewRepository.findMyReviewsOrderByIdDesc(request.getUserId(), pageRequest);
        }
        return reviewRepository.findMyReviewsOrderByIdDescWithCursor(request.getUserId(), cursorId, pageRequest);
    }

    private Slice<Review> findMyReviewsByRating(ReviewRequestDTO.MyReviewCursorRequest request) {
        RatingCursor cursor = parseRatingCursor(request.getCursor());
        PageRequest pageRequest = PageRequest.of(0, request.getSize());

        if (cursor == null) {
            return reviewRepository.findMyReviewsOrderByRatingDesc(request.getUserId(), pageRequest);
        }
        return reviewRepository.findMyReviewsOrderByRatingDescWithCursor(
                request.getUserId(),
                cursor.rating(),
                cursor.reviewId(),
                pageRequest
        );
    }

    private Long parseIdCursor(String cursor) {
        if (isBlank(cursor)) {
            return null;
        }

        String[] tokens = cursor.split(":");
        if (tokens.length != 2 || !SORT_BY_ID.equals(tokens[0])) {
            throw new ReviewException(ReviewErrorCode.INVALID_CURSOR);
        }

        try {
            Long cursorId = Long.parseLong(tokens[1]);
            if (cursorId <= 0) {
                throw new NumberFormatException();
            }
            return cursorId;
        } catch (NumberFormatException exception) {
            throw new ReviewException(ReviewErrorCode.INVALID_CURSOR);
        }
    }

    private RatingCursor parseRatingCursor(String cursor) {
        if (isBlank(cursor)) {
            return null;
        }

        String[] tokens = cursor.split(":");
        if (tokens.length != 3 || !SORT_BY_RATING.equals(tokens[0])) {
            throw new ReviewException(ReviewErrorCode.INVALID_CURSOR);
        }

        try {
            Integer rating = Integer.parseInt(tokens[1]);
            Long reviewId = Long.parseLong(tokens[2]);
            if (rating < 1 || rating > 5 || reviewId <= 0) {
                throw new NumberFormatException();
            }
            return new RatingCursor(rating, reviewId);
        } catch (NumberFormatException exception) {
            throw new ReviewException(ReviewErrorCode.INVALID_CURSOR);
        }
    }

    private boolean isBlank(String value) {
        return value == null || value.isBlank();
    }

    private record RatingCursor(Integer rating, Long reviewId) {
    }
}
