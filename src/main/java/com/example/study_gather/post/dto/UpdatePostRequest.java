package com.example.study_gather.post.dto;

import java.time.LocalDate;

public record UpdatePostRequest(
        Long categoryId,
        Long locationId,
        String title,
        String content,
        Integer maxNumber,
        Integer minNumber,
        Boolean isOnline,
        LocalDate startDate,
        LocalDate endDate
) {
}
