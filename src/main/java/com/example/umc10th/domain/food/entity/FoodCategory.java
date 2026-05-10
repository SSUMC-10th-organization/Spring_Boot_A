package com.example.umc10th.domain.food.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "food_category")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class FoodCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "food_category_id")
    private Long id;

    @Column(name = "food_name", nullable = false, length = 20)
    private String foodName;
}
