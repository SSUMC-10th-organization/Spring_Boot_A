package com.example.umc10th.domain.mission.entity;

import com.example.umc10th.domain.restaurant.entity.Restaurant;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "mission")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Mission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mission_id")
    private Long id;

    @Column(length = 50)
    private String title;

    @Column(length = 100)
    private String description;

    @Column(name = "reward_point")
    private Integer rewardPoint;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;
}
