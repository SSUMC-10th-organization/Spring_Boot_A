package com.example.umc10th.domain.review.service;

import com.example.umc10th.domain.mission.entity.mapping.UserMission;
import com.example.umc10th.domain.mission.repository.UserMissionRepository;
import com.example.umc10th.domain.restaurant.entity.Restaurant;
import com.example.umc10th.domain.restaurant.exception.RestaurantException;
import com.example.umc10th.domain.restaurant.exception.code.RestaurantErrorCode;
import com.example.umc10th.domain.restaurant.repository.RestaurantRepository;
import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.entity.Review;
import com.example.umc10th.domain.review.exception.ReviewException;
import com.example.umc10th.domain.review.exception.code.ReviewErrorCode;
import com.example.umc10th.domain.review.repository.ReviewRepository;
import com.example.umc10th.domain.user.entity.User;
import com.example.umc10th.domain.user.exception.UserException;
import com.example.umc10th.domain.user.exception.code.UserErrorCode;
import com.example.umc10th.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final RestaurantRepository restaurantRepository;
    private final UserMissionRepository userMissionRepository;

    @Override
    public ReviewResDTO.CreateReviewRes createReview(Long userId, Long restaurantId,
                                                      ReviewReqDTO.CreateReviewReq request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserException(UserErrorCode.USER_NOT_FOUND));

        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RestaurantException(RestaurantErrorCode.RESTAURANT_NOT_FOUND));

        UserMission userMission = userMissionRepository.findById(request.getUserMissionId())
                .orElseThrow(() -> new ReviewException(ReviewErrorCode.REVIEW_NOT_FOUND));

        Review review = Review.builder()
                .user(user)
                .restaurant(restaurant)
                .userMission(userMission)
                .rating(request.getRating())
                .body(request.getContent())
                .build();

        Review saved = reviewRepository.save(review);

        return ReviewResDTO.CreateReviewRes.builder()
                .reviewId(saved.getId())
                .authorNickname(user.getNickname())
                .rating(saved.getRating())
                .content(saved.getBody())
                .createdAt(saved.getCreatedAt())
                .build();
    }
}
