package com.example.umc.domain.review.service;

import com.example.umc.domain.review.code.ReviewErrorCode;
import com.example.umc.domain.review.converter.ReviewConverter;
import com.example.umc.domain.review.dto.ReviewRequestDTO;
import com.example.umc.domain.review.dto.ReviewResponseDTO;
import com.example.umc.domain.review.entity.Review;
import com.example.umc.domain.review.exception.ReviewException;
import com.example.umc.domain.review.repository.ReviewRepository;
import com.example.umc.domain.userMission.entity.UserMission;
import com.example.umc.domain.userMission.repository.UserMissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserMissionRepository userMissionRepository;

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
}
