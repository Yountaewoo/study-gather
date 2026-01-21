package com.example.study_gather.auth.comment.dto;

import java.time.LocalDateTime;

public record CommentResponse(
        Long postId,
        Long commentId,
        String content,
        LocalDateTime createdAt,
        Long userId,
        String memberNickname
) {
}
