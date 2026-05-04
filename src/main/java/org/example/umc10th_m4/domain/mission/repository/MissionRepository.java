package org.example.umc10th_m4.domain.mission.repository;

import org.example.umc10th_m4.domain.mission.entity.Mission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MissionRepository extends JpaRepository<Mission, Long> {

    @Query("SELECT m FROM Mission m WHERE m.store.region.id = :regionId")
    Page<Mission> findByRegionId(@Param("regionId") Long regionId, Pageable pageable);
}
