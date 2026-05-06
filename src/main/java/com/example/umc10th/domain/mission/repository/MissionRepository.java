package com.example.umc10th.domain.mission.repository;

import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.entity.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MissionRepository extends JpaRepository<Mission, Long> {

    // 특정 지역의 도전 가능한 미션 목록 (홈화면)
    @Query("SELECT m FROM Mission m JOIN m.store s WHERE s.location.id = :locationId")
    Page<Mission> findMissionsByLocation(@Param("locationId") Long locationId, Pageable pageable);
}