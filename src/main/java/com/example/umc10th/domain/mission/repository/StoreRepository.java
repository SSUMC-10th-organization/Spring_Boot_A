package com.example.umc10th.domain.mission.repository;

import com.example.umc10th.domain.mission.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {

}