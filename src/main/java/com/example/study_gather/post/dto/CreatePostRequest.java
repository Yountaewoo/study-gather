package com.example.study_gather.post.dto;

import jakarta.persistence.Column;

import java.time.LocalDate;

public record CreatePostRequest(
        Long categoryId,
        Long locationId,
        String title,
        String content,
        int maximumNumber,
        Boolean isOnline,
        LocalDate startDate,
        LocalDate endDate
) {
}
