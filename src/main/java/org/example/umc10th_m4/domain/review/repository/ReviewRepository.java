package org.example.umc10th_m4.domain.review.repository;

import org.example.umc10th_m4.domain.review.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query("SELECT r FROM Review r WHERE r.store.id = :storeId ORDER BY r.createdAt DESC")
    Page<Review> findByStoreId(@Param("storeId") Long storeId, Pageable pageable);

    @Query("SELECT r FROM Review r WHERE r.member.id = :memberId ORDER BY r.id DESC")
    Slice<Review> findByMemberIdOrderByIdDesc(@Param("memberId") Long memberId, Pageable pageable);

    @Query("SELECT r FROM Review r WHERE r.member.id = :memberId AND r.id < :cursor ORDER BY r.id DESC")
    Slice<Review> findByMemberIdAndIdLessThan(@Param("memberId") Long memberId, @Param("cursor") Long cursor, Pageable pageable);

    @Query("SELECT r FROM Review r WHERE r.member.id = :memberId ORDER BY r.score DESC, r.id DESC")
    Slice<Review> findByMemberIdOrderByScoreDescIdDesc(@Param("memberId") Long memberId, Pageable pageable);

    @Query("SELECT r FROM Review r WHERE r.member.id = :memberId AND (r.score < :score OR (r.score = :score AND r.id < :id)) ORDER BY r.score DESC, r.id DESC")
    Slice<Review> findByMemberIdWithScoreCursor(@Param("memberId") Long memberId, @Param("score") Integer score, @Param("id") Long id, Pageable pageable);
}
