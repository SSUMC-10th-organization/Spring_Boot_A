package com.example.umc10th.domain.review.repository;

import com.example.umc10th.domain.review.entity.Review;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    // ID 순 - 커서 없이
    @Query("SELECT r FROM Review r WHERE r.user.id = :userId ORDER BY r.id DESC")
    List<Review> findByUserIdOrderByIdDesc(
            @Param("userId") Long userId,
            Pageable pageable
    );

    // ID 순 - 커서 있을 때 (id < cursor)
    @Query("SELECT r FROM Review r WHERE r.user.id = :userId AND r.id < :cursor ORDER BY r.id DESC")
    List<Review> findByUserIdAndIdLessThanOrderByIdDesc(
            @Param("userId") Long userId,
            @Param("cursor") Long cursor,
            Pageable pageable
    );

    // 별점 순 - 커서 없이
    @Query("SELECT r FROM Review r WHERE r.user.id = :userId ORDER BY r.rating DESC, r.id DESC")
    List<Review> findByUserIdOrderByRatingDescIdDesc(
            @Param("userId") Long userId,
            Pageable pageable
    );

    // 별점 순 - 커서 있을 때 (rating < cursorRating OR (rating = cursorRating AND id < cursorId))
    @Query("SELECT r FROM Review r WHERE r.user.id = :userId " +
            "AND (r.rating < :rating OR (r.rating = :rating AND r.id < :id)) " +
            "ORDER BY r.rating DESC, r.id DESC")
    List<Review> findByUserIdWithRatingCursor(
            @Param("userId") Long userId,
            @Param("rating") Integer rating,
            @Param("id") Long id,
            Pageable pageable
    );
}
