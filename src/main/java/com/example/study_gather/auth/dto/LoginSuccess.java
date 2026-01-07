package com.example.study_gather.auth.dto;

import com.example.study_gather.member.dto.MemberInfo;

public record LoginSuccess(
        String accessToken,
        String refreshToken,
        long accessTokenTtlMinutes,
        long refreshTokenTtlDays,
        MemberInfo memberInfo
) {}
