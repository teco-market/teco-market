package com.teco.market.like.repository;

import static com.teco.market.like.QLike.*;
import static com.teco.market.post.QPost.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.support.PageableExecutionUtils;

import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.teco.market.like.web.MyLikeResponse;
import com.teco.market.like.QMyLikeResponse;
import com.teco.market.member.Member;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class LikeCustomRepositoryImpl implements LikeCustomRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public Page<MyLikeResponse> findMyLikes(Member member, Pageable pageable) {
        QueryResults<MyLikeResponse> results = queryFactory.select(new QMyLikeResponse(
            post.id.as("postId"),
            post.title.as("postTitle"),
            post.price.as("postPrice")
        ))
            .from(like)
            .leftJoin(like.post, post)
            .where(like.member.eq(member))
            .fetchResults();

        return PageableExecutionUtils.getPage(results.getResults(), pageable, results::getTotal);
    }
}
