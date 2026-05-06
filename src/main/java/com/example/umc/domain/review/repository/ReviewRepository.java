package com.example.umc.domain.review.repository;

import com.example.umc.domain.review.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    boolean existsByUserMissionId(Long userMissionId);

    @Query("""
            select r
            from Review r
            join fetch r.userMission um
            join fetch um.user u
            join fetch um.mission m
            where m.store.id = :storeId
              and r.deletedAt is null
            order by r.createdAt desc
            """)
    Page<Review> findReviewsByStoreId(@Param("storeId") Long storeId, Pageable pageable);
}
