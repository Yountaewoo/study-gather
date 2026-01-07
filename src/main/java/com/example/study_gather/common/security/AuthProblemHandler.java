package com.example.study_gather.common.security;

import java.io.IOException;

import com.example.study_gather.common.ApiResponse;
import com.example.study_gather.common.errorCode.AuthErrorCode;
import com.example.study_gather.common.exception.ErrorData;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import tools.jackson.databind.ObjectMapper;

@Component
@RequiredArgsConstructor
public class AuthProblemHandler implements AuthenticationEntryPoint, AccessDeniedHandler {
    private final ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException ex) throws IOException {
        AuthErrorCode errorCode = (AuthErrorCode) request.getAttribute("AUTH_ERROR_CODE");

        if (errorCode == null) errorCode = AuthErrorCode.AUTH_REQUIRED;
        write(response, errorCode);
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException ex) throws IOException {
        AuthErrorCode errorCode = (AuthErrorCode) request.getAttribute("AUTH_ERROR_CODE");
        if (errorCode == null) errorCode = AuthErrorCode.ACCESS_DENIED;
        write(response, errorCode);
    }

    private void write(HttpServletResponse response, AuthErrorCode errorCode) throws IOException {
        ErrorData errorData = new ErrorData(errorCode.getCode(), errorCode.getMessage());
        ApiResponse<ErrorData> body = ApiResponse.error(errorData, errorCode.getMessage());
        response.setStatus(errorCode.getHttpStatus().value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        objectMapper.writeValue(response.getOutputStream(), body);
    }
}
