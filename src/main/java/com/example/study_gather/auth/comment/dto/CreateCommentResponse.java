package com.example.study_gather.auth.comment.dto;

import com.example.study_gather.auth.comment.Comment;

public record CreateCommentResponse(
        Long commentId,
        Long postId,
        Long userId,
        String content
) {
    public static CreateCommentResponse toCommentResponse(Comment comment) {
        return new CreateCommentResponse(
                comment.getId(),
                comment.getPostId(),
                comment.getMemberId(),
                comment.getContent()
        );
    }
}
