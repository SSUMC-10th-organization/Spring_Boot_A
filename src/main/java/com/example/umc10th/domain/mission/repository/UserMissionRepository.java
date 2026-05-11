package com.example.umc10th.domain.mission.repository;

import com.example.umc10th.domain.mission.entity.mapping.UserMission;
import com.example.umc10th.domain.mission.enums.MissionStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserMissionRepository extends JpaRepository<UserMission, Long> {

    boolean existsByUserIdAndMissionId(Long userId, Long missionId);

    @Query("SELECT um FROM UserMission um " +
            "JOIN FETCH um.mission m " +
            "JOIN FETCH m.restaurant r " +
            "WHERE um.user.id = :userId AND um.status = :status")
    Page<UserMission> findByUserIdAndStatus(
            @Param("userId") Long userId,
            @Param("status") MissionStatus status,
            Pageable pageable
    );

    @Query("SELECT um FROM UserMission um " +
            "JOIN FETCH um.mission m " +
            "JOIN FETCH m.restaurant r " +
            "WHERE um.user.id = :userId AND um.status = 'ONGOING'")
    Page<UserMission> findOngoingMissionsByUserId(
            @Param("userId") Long userId,
            Pageable pageable
    );

    // 홈 화면: 특정 지역에서 유저가 아직 도전하지 않은 미션 목록
    @Query("SELECT m FROM Mission m " +
            "JOIN FETCH m.restaurant r " +
            "WHERE r.location.id = :locationId " +
            "AND m.id NOT IN (" +
            "   SELECT um.mission.id FROM UserMission um WHERE um.user.id = :userId" +
            ")")
    Page<com.example.umc10th.domain.mission.entity.Mission> findAvailableMissions(
            @Param("userId") Long userId,
            @Param("locationId") Long locationId,
            Pageable pageable
    );
}
