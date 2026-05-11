package com.example.umc10th.domain.review.repository;

import com.example.umc10th.domain.review.entity.Review;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    // ID 순 커서 기반 조회
    @Query("SELECT r FROM Review r WHERE r.member.id = :memberId AND r.id < :idCursor ORDER BY r.id DESC")
    Slice<Review> findByMemberIdAndIdLessThan(
            @Param("memberId") Long memberId,
            @Param("idCursor") Long idCursor,
            Pageable pageable
    );

    // ID 순 첫 조회 (커서 없을 때)
    @Query("SELECT r FROM Review r WHERE r.member.id = :memberId ORDER BY r.id DESC")
    Slice<Review> findByMemberIdOrderByIdDesc(
            @Param("memberId") Long memberId,
            Pageable pageable
    );

    // 별점 순 커서 기반 조회
    @Query("SELECT r FROM Review r WHERE r.member.id = :memberId " +
            "AND (r.star < :starCursor OR (r.star = :starCursor AND r.id < :idCursor)) " +
            "ORDER BY r.star DESC, r.id DESC")
    Slice<Review> findByMemberIdAndStarCursor(
            @Param("memberId") Long memberId,
            @Param("starCursor") Float starCursor,
            @Param("idCursor") Long idCursor,
            Pageable pageable
    );

    // 별점 순 첫 조회 (커서 없을 때)
    @Query("SELECT r FROM Review r WHERE r.member.id = :memberId ORDER BY r.star DESC, r.id DESC")
    Slice<Review> findByMemberIdOrderByStarDesc(
            @Param("memberId") Long memberId,
            Pageable pageable
    );
}