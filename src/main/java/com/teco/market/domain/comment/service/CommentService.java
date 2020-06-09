package com.teco.market.domain.comment.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.teco.market.domain.comment.Comment;
import com.teco.market.domain.comment.CommentRepository;
import com.teco.market.domain.member.Member;
import com.teco.market.domain.post.Post;
import com.teco.market.domain.post.PostRepository;
import com.teco.market.exception.InvalidWriterException;
import com.teco.market.exception.NotFoundCommentException;
import com.teco.market.exception.notfound.NotFoundPostException;
import com.teco.market.web.dto.CommentRequest;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Transactional
@Service
public class CommentService {
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    public void save(Long postId, CommentRequest commentRequest, Member member) {
        Post post = postRepository.findById(postId)
            .orElseThrow(NotFoundPostException::new);
        Comment comment = Comment.builder()
            .post(post)
            .member(member)
            .content(commentRequest.getContent())
            .build();

        commentRepository.save(comment);
    }

    public void update(Long id, Member member, CommentUpdateRequest request) {
        Comment comment = findById(id);
        validateWriter(member, comment);
        comment.changeContent(request.getContent());
    }

    public void delete(Long id, Member member) {
        Comment comment = findById(id);
        validateWriter(member, comment);
        commentRepository.delete(comment);
    }

    private Comment findById(Long id) {
        return commentRepository.findById(id)
            .orElseThrow(NotFoundCommentException::new);
    }

    private void validateWriter(Member member, Comment comment) {
        if (comment.isNotWrittenBy(member)) {
            throw new InvalidWriterException();
        }
    }
}
