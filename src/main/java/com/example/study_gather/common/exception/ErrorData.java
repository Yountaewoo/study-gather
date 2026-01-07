package com.example.study_gather.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorData {
    private String code;
    private Object details;	// String 또는 List<...>
}
