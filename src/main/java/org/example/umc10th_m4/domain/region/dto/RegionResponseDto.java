package org.example.umc10th_m4.domain.region.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RegionResponseDto {
    private long regionId;
    private String name;
}