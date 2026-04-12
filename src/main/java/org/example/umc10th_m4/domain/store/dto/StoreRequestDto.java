package org.example.umc10th_m4.domain.store.dto;

import lombok.Getter;

public class StoreRequestDto {
    @Getter
    public static class UpdateStoreDto {
        private String name;
        private String location;
    }
}