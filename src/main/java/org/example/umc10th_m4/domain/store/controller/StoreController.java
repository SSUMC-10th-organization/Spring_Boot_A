package org.example.umc10th_m4.domain.store.controller;

import lombok.RequiredArgsConstructor;
import org.example.umc10th_m4.domain.store.dto.StoreRequestDto;
import org.example.umc10th_m4.domain.store.dto.StoreResponseDto;
import org.example.umc10th_m4.global.common.ApiResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/stores")
public class StoreController {

//    @GetMapping("/{storeId}")
//    public ApiResponse<StoreResponseDto> getStore(@PathVariable(name = "storeId") long storeId) {
//        return ApiResponse.onSuccess(null);
//    }
//
//    @PatchMapping("/{storeId}")
//    public ApiResponse<StoreResponseDto> updateStore(
//            @PathVariable(name = "storeId") long storeId,
//            @RequestBody StoreRequestDto.UpdateStoreDto request) {
//        return ApiResponse.onSuccess(null);
//    }
}