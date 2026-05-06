package com.example.umc.domain.userPreference.repository;

import com.example.umc.domain.userPreference.entity.UserPreference;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserPreferenceRepository extends JpaRepository<UserPreference, Long> {
}
