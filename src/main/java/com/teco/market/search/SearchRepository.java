package com.teco.market.search;

import static com.teco.market.post.QPost.*;
import static org.springframework.util.StringUtils.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.teco.market.post.web.PostResponse;
import com.teco.market.post.web.QPostResponse;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Repository
public class SearchRepository {
    private final JPAQueryFactory jpaQueryFactory;

    public Page<PostResponse> search(SearchCondition searchCondition, Pageable pageable) {
        OrderConditions orderConditions = new OrderConditions(searchCondition);
        QueryResults<PostResponse> result = jpaQueryFactory.select(new QPostResponse(
            post.thumbnail.url.as("thumbnail"),
            post.title.as("title"),
            post.member.name.as("name"),
            post.member.nickname.as("nickname"),
            post.category.name.as("category"),
            post.price.as("price")
        ))
            .from(post)
            .where(categoryEq(searchCondition.getCategoryId()),
                keywordEq(searchCondition.getKeyword()),
                authorEq(searchCondition.getAuthor())
            ).orderBy(orderConditions.get())
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetchResults();

        return PageableExecutionUtils.getPage(result.getResults(), pageable, result::getTotal);
    }

    private BooleanExpression authorEq(String author) {
        return isEmpty(author) ? null : post.member.nickname.eq(author);
    }

    private BooleanExpression keywordEq(String keyword) {
        return isEmpty(keyword) ? null : post.title.containsIgnoreCase(keyword);
    }

    private BooleanExpression categoryEq(Long categoryId) {
        return isEmpty(categoryId) ? null : post.category.id.eq(categoryId);
    }
}
