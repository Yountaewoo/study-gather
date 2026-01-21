package com.example.study_gather.post;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findAllByMemberIdAndIsActiveTrueOrderByCreatedAtDesc(Long memberId);
}
