package com.example.study_gather.post.dto;

import java.util.List;

public record FilterPostRequest(
        List<Long> locationIds,
        List<Long> categoryIds,
        Integer minNumber,
        Integer maxNumber,
        Boolean isOnline
) {
}
