package com.example.study_gather.post.dto;

import com.example.study_gather.post.Post;
import com.example.study_gather.post.PostService;

import java.time.LocalDate;

public record CreatePostResponse(
        Long postId,
        Long categoryId,
        Long locationId,
        String title,
        String content,
        int maximumNumber,
        boolean isActive,
        LocalDate startDate,
        LocalDate endDate
) {
    public static CreatePostResponse toCreatePostResponse(Post post) {
        return new CreatePostResponse(post.getId(),
                post.getCategoryId(),
                post.getLocationId(),
                post.getTitle(),
                post.getContent(),
                post.getMaximumNumber(),
                post.getIsActive(),
                post.getStartDate(),
                post.getEndDate());
    }
}
