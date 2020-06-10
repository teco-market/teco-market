package com.teco.market.comment.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.teco.market.comment.repository.CommentRepository;
import com.teco.market.comment.web.MyCommentResponse;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CommentQueryService {
    private final CommentRepository commentRepository;

    public Page<MyCommentResponse> findMyComments(Long id, Pageable pageable) {
        return commentRepository.findByMemberId(id, pageable);
    }
}
