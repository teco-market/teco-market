package com.teco.market.comment.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.teco.market.comment.MyCommentResponse;
import com.teco.market.comment.CommentCustomRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CommentQueryService {
    private final CommentCustomRepository commentCustomRepository;

    public Page<MyCommentResponse> findMyComments(Long id, Pageable pageable) {
        return commentCustomRepository.findByMemberId(id, pageable);
    }
}
