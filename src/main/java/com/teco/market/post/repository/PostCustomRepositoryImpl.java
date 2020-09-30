package com.teco.market.post.repository;

import static com.teco.market.category.QCategory.*;
import static com.teco.market.member.domain.QMember.*;
import static com.teco.market.post.QPost.*;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.support.PageableExecutionUtils;

import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.teco.market.post.web.PostResponse;
import com.teco.market.post.web.QPostResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PostCustomRepositoryImpl implements PostCustomRepository {
    private static final int REPRESENTATIVE_COUNT = 15;

    private final JPAQueryFactory queryFactory;

    @Override
    public List<PostResponse> findRepresentativePosts() {
        return queryFactory.select(new QPostResponse(
            post.as("post"),
            member.as("member"),
            category.as("category")
        ))
            .from(post)
            .innerJoin(post.category, category)
            .innerJoin(post.member, member)
            .limit(REPRESENTATIVE_COUNT)
            .fetch();
    }

    @Override
    public Page<PostResponse> findPosts(Pageable pageable) {
        QueryResults<PostResponse> results = queryFactory.select(new QPostResponse(
            post.as("post"),
            member.as("member"),
            category.as("category")
        ))
            .from(post)
            .leftJoin(post.category, category)
            .leftJoin(post.member, member)
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetchResults();

        return PageableExecutionUtils.getPage(results.getResults(), pageable, results::getTotal);
    }
}
