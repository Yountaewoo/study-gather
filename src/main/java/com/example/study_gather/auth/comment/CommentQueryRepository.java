package com.example.study_gather.auth.comment;

import com.example.study_gather.auth.comment.dto.CommentResponse;
import com.example.study_gather.member.QMember;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Slf4j
@RequiredArgsConstructor
public class CommentQueryRepository {

    private final JPAQueryFactory queryFactory;
    private final QComment comment = QComment.comment;
    private final QMember member = QMember.member;

    public List<CommentResponse> findByPostId(Long postId) {
        return queryFactory
                .select(Projections.constructor(CommentResponse.class,
                        comment.postId,
                        comment.id,
                        comment.content,
                        comment.createdAt,
                        comment.memberId,
                        member.nickname))
                .from(comment)
                .join(member).on(member.id.eq(comment.memberId))
                .where(comment.postId.eq(postId))
                .orderBy(comment.createdAt.desc())
                .fetch();
    }
}
