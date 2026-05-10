package org.example.umc10th_m4.global.common;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class CursorPageResponse<T> {
    private List<T> content;
    private Boolean hasNext;
    private String nextCursor;
    private int size;
}
