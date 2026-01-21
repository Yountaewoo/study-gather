package com.example.study_gather.post.dto;

import com.example.study_gather.post.PostResponse;

import java.util.List;

public record PostListResponse(
        List<PostResponse> postResponses
) {
}
