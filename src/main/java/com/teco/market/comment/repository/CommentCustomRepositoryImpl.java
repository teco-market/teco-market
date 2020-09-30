package com.teco.market.comment.repository;

import static com.teco.market.comment.QComment.*;
import static com.teco.market.member.domain.QMember.*;
import static com.teco.market.post.QPost.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.support.PageableExecutionUtils;

import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.teco.market.comment.web.MyCommentResponse;
import com.teco.market.comment.web.QMyCommentResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CommentCustomRepositoryImpl implements CommentCustomRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public Page<MyCommentResponse> findByMemberId(Long id, Pageable pageable) {
        QueryResults<MyCommentResponse> results = queryFactory.select(new QMyCommentResponse(
            post.title.as("postTitle"),
            comment.content.as("commentContent")
        ))
            .from(comment)
            .leftJoin(comment.member, member)
            .leftJoin(comment.post, post)
            .where(comment.member.id.eq(id))
            .fetchResults();
        return PageableExecutionUtils.getPage(results.getResults(), pageable, results::getTotal);
    }
}
