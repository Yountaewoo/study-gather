package com.example.study_gather.location.dto;

public record CreateLocationResponse(
        Long id,
        Long parentId,
        String name
) {
}
