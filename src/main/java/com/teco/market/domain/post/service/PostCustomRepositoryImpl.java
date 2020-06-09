package com.teco.market.domain.post.service;

import static com.teco.market.domain.category.QCategory.*;
import static com.teco.market.domain.image.QThumbnail.*;
import static com.teco.market.domain.member.QMember.*;
import static com.teco.market.domain.post.QPost.*;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.support.PageableExecutionUtils;

import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.teco.market.domain.post.PostCustomRepository;
import com.teco.market.web.dto.PostResponse;
import com.teco.market.web.dto.QPostResponse;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PostCustomRepositoryImpl implements PostCustomRepository {
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
            .limit(15)
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
