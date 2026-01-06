package com.example.study_gather.category.dto;

public record CreateCategoryRequest(
        Long parentId,
        String name
) {
}
