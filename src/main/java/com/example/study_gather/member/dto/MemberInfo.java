package com.example.study_gather.member.dto;

import com.example.study_gather.member.Gender;
import com.example.study_gather.member.Member;
import com.example.study_gather.member.Role;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record MemberInfo(Long memberId,
                         String userLoginId,
                         String memberName,
                         String memberEmail,
                         String nickname,
                         String memberImage,
                         Gender gender,
                         Role role,
                         LocalDate memberBirth,
                         LocalDateTime createdTime) {

    public static MemberInfo of(Member member) {
        return new MemberInfo(member.getId(),
                member.getUserLoginId(),
                member.getMemberName(),
                member.getMemberEmail(),
                member.getNickname(),
                member.getMemberImage(),
                member.getGender(),
                member.getRole(),
                member.getBirth(),
                member.getCreatedAt());
    }
}
