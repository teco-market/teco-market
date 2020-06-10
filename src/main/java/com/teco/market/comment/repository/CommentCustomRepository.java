package com.teco.market.comment.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.teco.market.comment.web.MyCommentResponse;

@Repository
public interface CommentCustomRepository {
    Page<MyCommentResponse> findByMemberId(Long id, Pageable pageable);
}
