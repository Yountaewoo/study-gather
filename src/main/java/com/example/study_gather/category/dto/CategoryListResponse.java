package com.example.study_gather.category.dto;

import com.example.study_gather.category.CategoryResponse;

import java.util.List;

public record CategoryListResponse(
        List<CategoryResponse> categoryResponseList
) {
}
