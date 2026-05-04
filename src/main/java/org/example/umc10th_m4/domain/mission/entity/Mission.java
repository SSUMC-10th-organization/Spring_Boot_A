package org.example.umc10th_m4.domain.mission.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.umc10th_m4.domain.store.entity.Store;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Mission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String detail;
    private Integer point;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;
}
