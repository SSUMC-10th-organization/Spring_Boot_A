package com.example.umc10th.domain.mission.repository;

import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.mission.entity.UserMission;
import com.example.umc10th.domain.mission.enums.MissionStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMissionRepository extends JpaRepository<UserMission, Long> {

    // 내 미션 목록 조회 (진행중, 진행완료 페이징)
    @Query("SELECT um FROM UserMission um WHERE um.member = :member AND um.status = :status")
    Page<UserMission> findByMemberAndStatus(
            @Param("member") Member member,
            @Param("status") MissionStatus status,
            Pageable pageable
    );
}