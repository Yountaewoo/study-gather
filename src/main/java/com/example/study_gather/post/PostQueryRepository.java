package com.example.study_gather.post;

import com.example.study_gather.post.dto.FilterPostRequest;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@Slf4j
@RequiredArgsConstructor
public class PostQueryRepository {

    private final JPAQueryFactory queryFactory;
    private final QPost post = QPost.post;

    public List<Post> searchPost(List<Long> locationIds,
                                 List<Long> categoryIds,
                                 Integer minNumber,
                                 Integer maxNumber,
                                 Boolean isOnline) {
        return queryFactory
                .selectFrom(post)
                .where(
                        findByLocationIds(locationIds),
                        findByCategoryIds(categoryIds),
                        peopleRange(minNumber, maxNumber),
                        findByIsOnline(isOnline)
                )
                .fetch();
    }

    private BooleanExpression findByLocationIds(List<Long> locationIds) {
        if (locationIds == null) {
            return null;
        }
        return post.locationId.in(locationIds);
    }

    private BooleanExpression findByCategoryIds(List<Long> categoryIds) {
        if (categoryIds == null) {
            return null;
        }
        return post.categoryId.in(categoryIds);
    }

    private BooleanExpression peopleRange(Integer minNumber, Integer maxNumber) {
        if (minNumber == null && maxNumber == null) {
            return null;
        } else if (minNumber == null && maxNumber != null) {
            return post.minimumNumber.goe(Integer.MIN_VALUE).and(post.maximumNumber.loe(maxNumber));
        } else if (minNumber != null && maxNumber == null) {
            return post.minimumNumber.goe(minNumber).and(post.maximumNumber.loe(Integer.MAX_VALUE));
        }
        return post.minimumNumber.goe(minNumber).and(post.maximumNumber.loe(maxNumber));
    }

    private BooleanExpression findByIsOnline(Boolean isOnline) {
        if (isOnline == null) {
            return null;
        }
        return post.isOnline.eq(isOnline);
    }
}
