package com.example.umc10th.domain.food.repository;

import com.example.umc10th.domain.food.entity.FoodCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodCategoryRepository extends JpaRepository<FoodCategory, Long> {
}
