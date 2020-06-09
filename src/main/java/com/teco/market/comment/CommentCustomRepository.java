package com.teco.market.comment;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentCustomRepository {
    Page<MyCommentResponse> findByMemberId(Long id, Pageable pageable);
}
