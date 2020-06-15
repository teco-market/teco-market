package com.teco.market.post.repository;

import static com.teco.market.category.QCategory.*;
import static com.teco.market.image.QThumbnail.*;
import static com.teco.market.member.QMember.*;
import static com.teco.market.post.QPost.*;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.support.PageableExecutionUtils;

import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.teco.market.post.web.PostResponse;
import com.teco.market.post.web.QPostResponse;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PostCustomRepositoryImpl implements PostCustomRepository {
    private static final int REPRESENTATIVE_COUNT = 15;
    private final JPAQueryFactory queryFactory;

    @Override
    public List<PostResponse> findRepresentativePosts() {
        return queryFactory.select(new QPostResponse(
            thumbnail.url.as("thumbnail"),
            post.title.as("title"),
            member.name.as("name"),
            member.nickname.as("nickname"),
            category.name.as("category"),
            post.price.as("price")
        ))
            .from(post)
            .leftJoin(post.thumbnail, thumbnail)
            .leftJoin(post.category, category)
            .leftJoin(post.member, member)
            .limit(REPRESENTATIVE_COUNT)
            .fetch();
    }

    @Override
    public Page<PostResponse> findPosts(Pageable pageable) {
        QueryResults<PostResponse> results = queryFactory.select(new QPostResponse(
            thumbnail.url.as("thumbnail"),
            post.title.as("title"),
            member.name.as("name"),
            member.nickname.as("nickname"),
            category.name.as("category"),
            post.price.as("price")
        ))
            .from(post)
            .leftJoin(post.thumbnail, thumbnail)
            .leftJoin(post.category, category)
            .leftJoin(post.member, member)
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetchResults();

        return PageableExecutionUtils.getPage(results.getResults(), pageable, results::getTotal);
    }
}
