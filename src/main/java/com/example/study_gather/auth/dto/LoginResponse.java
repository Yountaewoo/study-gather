package com.example.study_gather.auth.dto;

import com.example.study_gather.member.dto.MemberInfo;

public record LoginResponse(MemberInfo user) {
    public static LoginResponse of(MemberInfo user) {
        return new LoginResponse(user);
    }
}
