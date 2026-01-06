package com.example.study_gather.category.dto;

public record CreateCategoryResponse(
        Long id,
        Long parentId,
        String name
) {
}
