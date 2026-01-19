package com.example.study_gather.auth.comment.dto;

public record CommentResponse(
        Long commentId,
        Long postId,
        Long userId,
        String content
) {
}
