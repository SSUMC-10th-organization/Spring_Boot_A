package org.example.umc10th_m4.domain.store.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.umc10th_m4.domain.region.entity.Region;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String location;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id")
    private Region region;
}
