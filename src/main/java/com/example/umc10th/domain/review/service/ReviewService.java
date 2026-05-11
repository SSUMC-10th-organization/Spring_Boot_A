package com.example.umc10th.domain.review.service;

import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.repository.MemberRepository;
import com.example.umc10th.domain.mission.entity.Store;
import com.example.umc10th.domain.mission.repository.StoreRepository;
import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.entity.Review;
import com.example.umc10th.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;

    // 리뷰 생성
    @Transactional
    public ReviewResDTO.ReviewCreateResult createReview(
            Long memberId, ReviewReqDTO.ReviewCreate request) {

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 회원입니다."));

        Store store = storeRepository.findById(request.storeId())
                .orElseThrow(() -> new RuntimeException("존재하지 않는 가게입니다."));

        Review review = Review.builder()
                .member(member)
                .store(store)
                .star(request.score())
                .content(request.body())
                .build();

        Review savedReview = reviewRepository.save(review);

        return new ReviewResDTO.ReviewCreateResult(
                savedReview.getId(),
                savedReview.getCreatedAt()
        );
    }

    // 내가 생성한 리뷰 조회 (커서 기반)
    public ReviewResDTO.ReviewListResult getMyReviews(
            Long memberId, int pageSize, String cursor, String query) {

        PageRequest pageRequest = PageRequest.of(0, pageSize);
        Slice<Review> reviewSlice;
        String nextCursor;

        // 커서가 있는 경우
        if (!cursor.equals("-1")) {
            String[] cursorSplit = cursor.split(":");

            switch (query.toLowerCase()) {
                case "id" -> {
                    Long idCursor = Long.parseLong(cursorSplit[1]);
                    reviewSlice = reviewRepository
                            .findByMemberIdAndIdLessThan(memberId, idCursor, pageRequest);
                }
                case "star" -> {
                    Float starCursor = Float.parseFloat(cursorSplit[0]);
                    Long idCursor = Long.parseLong(cursorSplit[1]);
                    reviewSlice = reviewRepository
                            .findByMemberIdAndStarCursor(memberId, starCursor, idCursor, pageRequest);
                }
                default -> throw new RuntimeException("지원하지 않는 정렬 방식입니다.");
            }
        } else {
            // 커서가 없는 경우 (첫 조회)
            reviewSlice = switch (query.toLowerCase()) {
                case "id" -> reviewRepository
                        .findByMemberIdOrderByIdDesc(memberId, pageRequest);
                case "star" -> reviewRepository
                        .findByMemberIdOrderByStarDesc(memberId, pageRequest);
                default -> throw new RuntimeException("지원하지 않는 정렬 방식입니다.");
            };
        }

        // 다음 커서 계산
        if (reviewSlice.hasNext()) {
            Review lastReview = reviewSlice.getContent()
                    .get(reviewSlice.getContent().size() - 1);
            nextCursor = lastReview.getStar() + ":" + lastReview.getId();
        } else {
            nextCursor = null;
        }

        List<ReviewResDTO.ReviewDetail> reviewDetails = reviewSlice.stream()
                .map(r -> new ReviewResDTO.ReviewDetail(
                        r.getId(),
                        r.getStore().getName(),
                        r.getStar(),
                        r.getContent(),
                        r.getCreatedAt()
                )).toList();

        return new ReviewResDTO.ReviewListResult(
                reviewDetails,
                reviewSlice.hasNext(),
                nextCursor,
                reviewSlice.getSize()
        );
    }
}