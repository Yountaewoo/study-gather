package com.example.study_gather.auth.comment.dto;

public record CreateCommentRequest(
        Long postId,
        String content
) {
}
