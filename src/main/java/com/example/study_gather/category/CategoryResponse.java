package com.example.study_gather.category;

public record CategoryResponse(
        Long id,
        Long parentId,
        String name
) {
}
