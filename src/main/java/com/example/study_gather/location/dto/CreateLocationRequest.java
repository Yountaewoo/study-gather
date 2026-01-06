package com.example.study_gather.location.dto;

public record CreateLocationRequest(
        Long parentId,
        String name
) {
}
