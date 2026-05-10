package org.example.umc10th_m4.domain.review.service;

import lombok.RequiredArgsConstructor;
import org.example.umc10th_m4.domain.member.entity.Member;
import org.example.umc10th_m4.domain.member.error.MemberErrorStatus;
import org.example.umc10th_m4.domain.member.repository.MemberRepository;
import org.example.umc10th_m4.domain.review.dto.ReviewRequestDto;
import org.example.umc10th_m4.domain.review.dto.ReviewResponseDto;
import org.example.umc10th_m4.domain.review.entity.Review;
import org.example.umc10th_m4.domain.review.repository.ReviewRepository;
import org.example.umc10th_m4.domain.store.entity.Store;
import org.example.umc10th_m4.domain.store.error.StoreErrorStatus;
import org.example.umc10th_m4.domain.store.repository.StoreRepository;
import org.example.umc10th_m4.global.common.CursorPageResponse;
import org.example.umc10th_m4.global.status.GeneralException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewServiceImpl implements ReviewService {

    private static final int PAGE_SIZE = 10;

    private final ReviewRepository reviewRepository;
    private final StoreRepository storeRepository;
    private final MemberRepository memberRepository;

    @Override
    public ReviewResponseDto addReview(long storeId, ReviewRequestDto request) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new GeneralException(StoreErrorStatus.STORE_NOT_FOUND));
        Member member = memberRepository.findById(request.getMemberId())
                .orElseThrow(() -> new GeneralException(MemberErrorStatus.MEMBER_NOT_FOUND));

        Review review = Review.builder()
                .store(store)
                .member(member)
                .region(store.getRegion())
                .score(request.getScore())
                .detail(request.getDetail())
                .build();

        Review saved = reviewRepository.save(review);
        return toDto(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReviewResponseDto> getReviews(long storeId, int page) {
        storeRepository.findById(storeId)
                .orElseThrow(() -> new GeneralException(StoreErrorStatus.STORE_NOT_FOUND));

        return reviewRepository.findByStoreId(storeId, PageRequest.of(page - 1, PAGE_SIZE))
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public CursorPageResponse<ReviewResponseDto> getMyReviews(long memberId, String query, String cursor, int size) {
        memberRepository.findById(memberId)
                .orElseThrow(() -> new GeneralException(MemberErrorStatus.MEMBER_NOT_FOUND));

        Pageable pageable = PageRequest.of(0, size);
        Slice<Review> slice;

        if ("score".equals(query)) {
            if (cursor != null) {
                // cursor 형식: "score:{lastScore}:{lastId}"
                String[] parts = cursor.split(":");
                int lastScore = Integer.parseInt(parts[1]);
                long lastId = Long.parseLong(parts[2]);
                slice = reviewRepository.findByMemberIdWithScoreCursor(memberId, lastScore, lastId, pageable);
            } else {
                slice = reviewRepository.findByMemberIdOrderByScoreDescIdDesc(memberId, pageable);
            }
        } else {
            // 기본값: id 순
            if (cursor != null) {
                // cursor 형식: "id:{lastId}"
                String[] parts = cursor.split(":");
                long lastId = Long.parseLong(parts[1]);
                slice = reviewRepository.findByMemberIdAndIdLessThan(memberId, lastId, pageable);
            } else {
                slice = reviewRepository.findByMemberIdOrderByIdDesc(memberId, pageable);
            }
        }

        List<ReviewResponseDto> content = slice.getContent().stream()
                .map(this::toDto)
                .collect(Collectors.toList());

        String nextCursor = null;
        if (slice.hasNext() && !content.isEmpty()) {
            Review last = slice.getContent().get(slice.getContent().size() - 1);
            if ("score".equals(query)) {
                nextCursor = "score:" + last.getScore() + ":" + last.getId();
            } else {
                nextCursor = "id:" + last.getId();
            }
        }

        return CursorPageResponse.<ReviewResponseDto>builder()
                .content(content)
                .hasNext(slice.hasNext())
                .nextCursor(nextCursor)
                .size(content.size())
                .build();
    }

    private ReviewResponseDto toDto(Review review) {
        return ReviewResponseDto.builder()
                .reviewId(review.getId())
                .storeName(review.getStore().getName())
                .memberName(review.getMember().getName())
                .score(review.getScore())
                .detail(review.getDetail())
                .createdAt(review.getCreatedAt() != null ? review.getCreatedAt().toString() : null)
                .build();
    }
}
