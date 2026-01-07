package com.example.study_gather.member;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    boolean existsByUserLoginIdAndDeletedAtIsNull(String userLoginId);

    boolean existsByMemberEmailAndDeletedAtIsNull(String memberEmail);

    boolean existsByNicknameAndDeletedAtIsNull(String nickname);

    Optional<Member> findByUserLoginIdAndDeletedAtIsNull(String userLoginId);

    Optional<Member> findByIdAndDeletedAtIsNull(Long id);
}
