package com.example.study_gather.auth;

import com.example.study_gather.auth.dto.*;
import com.example.study_gather.common.ApiResponse;
import com.example.study_gather.common.web.CookieUtil;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private static final String ACCESS_TOKEN_COOKIE_NAME = "__Host-AT";
    private static final String REFRESH_TOKEN_COOKIE_NAME = "__Host-RT";

    private final AuthService authService;

    @PostMapping("/login")
    public ApiResponse<LoginResponse> login(@RequestBody @Valid LoginRequest request, HttpServletResponse response) {

        LoginSuccess result = authService.login(request);

        CookieUtil.addAuthCookies(response, result.accessToken(), result.accessTokenTtlMinutes(), result.refreshToken(), result.refreshTokenTtlDays());

        return ApiResponse.success(LoginResponse.of(result.memberInfo()));
    }

    @PostMapping("/logout")
    public ApiResponse<String> logout(HttpServletRequest request, HttpServletResponse response) {

        String accessToken = CookieUtil.getCookieValue(request, ACCESS_TOKEN_COOKIE_NAME);
        String refreshToken = CookieUtil.getCookieValue(request, REFRESH_TOKEN_COOKIE_NAME);

        authService.logout(accessToken, refreshToken);

        CookieUtil.clearAuthCookies(response);

        return ApiResponse.success("Logged out");
    }

    @PostMapping("/signup")
    public ApiResponse<SignupResponse> signup(@RequestBody @Valid SignupRequest request) {

        return ApiResponse.success(authService.register(request));
    }
}
