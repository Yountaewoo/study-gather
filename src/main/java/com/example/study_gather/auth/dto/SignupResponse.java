package com.example.study_gather.auth.dto;

import com.example.study_gather.member.Member;
import com.example.study_gather.member.dto.MemberInfo;

public record SignupResponse(MemberInfo member) {
    public static SignupResponse of(Member member) {
        return new SignupResponse(MemberInfo.of(member));
    }
}