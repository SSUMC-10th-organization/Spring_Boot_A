package com.example.umc.domain.userMission.repository;

import com.example.umc.domain.userMission.entity.UserMission;
import com.example.umc.domain.userMission.entity.UserMissionStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserMissionRepository extends JpaRepository<UserMission, Long> {

    @Query("""
            select um
            from UserMission um
            join fetch um.mission m
            join fetch m.store s
            where um.user.id = :userId
              and um.status = :status
              and um.deletedAt is null
            """)
    Page<UserMission> findUserMissionsByStatus(
            @Param("userId") Long userId,
            @Param("status") UserMissionStatus status,
            Pageable pageable
    );
}
