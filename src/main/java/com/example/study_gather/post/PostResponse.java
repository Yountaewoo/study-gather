package com.example.study_gather.post;

import java.time.LocalDate;

public record PostResponse(
        Long postId,
        Long categoryId,
        Long locationId,
        String title,
        String content,
        Integer maxNumber,
        Integer minNumber,
        boolean isActive,
        LocalDate startDate,
        LocalDate endDate
) {
    public static PostResponse toPostResponse(Post post) {
        return new PostResponse(
                post.getId(),
                post.getCategoryId(),
                post.getLocationId(),
                post.getTitle(),
                post.getContent(),
                post.getMaxNumber(),
                post.getMinNumber(),
                post.getIsActive(),
                post.getStartDate(),
                post.getEndDate());
    }
}
