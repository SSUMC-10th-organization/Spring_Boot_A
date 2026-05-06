package org.example.umc10th_m4.domain.store.repository;

import org.example.umc10th_m4.domain.store.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Long> {
}
