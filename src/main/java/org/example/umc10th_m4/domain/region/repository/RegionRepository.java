package org.example.umc10th_m4.domain.region.repository;

import org.example.umc10th_m4.domain.region.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RegionRepository extends JpaRepository<Region, Long> {
    Optional<Region> findByName(String name);
}
