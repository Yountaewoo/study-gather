package com.example.study_gather.post.dto;

import com.example.study_gather.auth.comment.Comment;
import com.example.study_gather.auth.comment.dto.CommentResponse;
import com.example.study_gather.post.Post;

import java.time.LocalDate;
import java.util.List;

public record PostDetailResponse(
        Long postId,
        Long categoryId,
        Long locationId,
        String title,
        String content,
        Integer maxNumber,
        Integer minNumber,
        List<CommentResponse> commentResponses,
        boolean isActive,
        Long memberId,
        LocalDate startDate,
        LocalDate endDate
) {
    public static PostDetailResponse toPostDetailResponse(Post post, List<CommentResponse> comments) {
        return new PostDetailResponse(
                post.getId(),
                post.getCategoryId(),
                post.getLocationId(),
                post.getTitle(),
                post.getContent(),
                post.getMaxNumber(),
                post.getMinNumber(),
                comments,
                post.getIsActive(),
                post.getMemberId(),
                post.getStartDate(),
                post.getEndDate());
    }
}

