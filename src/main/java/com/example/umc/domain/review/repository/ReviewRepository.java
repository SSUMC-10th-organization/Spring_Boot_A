package com.example.umc.domain.review.repository;

import com.example.umc.domain.review.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    boolean existsByUserMissionId(Long userMissionId);

    @Query(value = """
            select r
            from Review r
            join fetch r.userMission um
            join fetch um.user u
            join fetch um.mission m
            where m.store.id = :storeId
              and r.deletedAt is null
            order by r.createdAt desc
            """,
            countQuery = """
                    select count(r)
                    from Review r
                    join r.userMission um
                    join um.mission m
                    where m.store.id = :storeId
                      and r.deletedAt is null
                    """)
    Page<Review> findReviewsByStoreId(@Param("storeId") Long storeId, Pageable pageable);

    @Query("""
            select r
            from Review r
            join fetch r.userMission um
            join fetch um.mission m
            join fetch m.store s
            where um.user.id = :userId
              and r.deletedAt is null
            order by r.id desc
            """)
    Slice<Review> findMyReviewsOrderByIdDesc(
            @Param("userId") Long userId,
            Pageable pageable
    );

    @Query("""
            select r
            from Review r
            join fetch r.userMission um
            join fetch um.mission m
            join fetch m.store s
            where um.user.id = :userId
              and r.deletedAt is null
              and r.id < :cursorId
            order by r.id desc
            """)
    Slice<Review> findMyReviewsOrderByIdDescWithCursor(
            @Param("userId") Long userId,
            @Param("cursorId") Long cursorId,
            Pageable pageable
    );

    @Query("""
            select r
            from Review r
            join fetch r.userMission um
            join fetch um.mission m
            join fetch m.store s
            where um.user.id = :userId
              and r.deletedAt is null
            order by r.rating desc, r.id desc
            """)
    Slice<Review> findMyReviewsOrderByRatingDesc(
            @Param("userId") Long userId,
            Pageable pageable
    );

    @Query("""
            select r
            from Review r
            join fetch r.userMission um
            join fetch um.mission m
            join fetch m.store s
            where um.user.id = :userId
              and r.deletedAt is null
              and (r.rating < :cursorRating or (r.rating = :cursorRating and r.id < :cursorId))
            order by r.rating desc, r.id desc
            """)
    Slice<Review> findMyReviewsOrderByRatingDescWithCursor(
            @Param("userId") Long userId,
            @Param("cursorRating") Integer cursorRating,
            @Param("cursorId") Long cursorId,
            Pageable pageable
    );
}
