package com.teco.market.post.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.teco.market.comment.Comment;
import com.teco.market.comment.repository.CommentRepository;
import com.teco.market.common.exception.notfound.NotFoundPostException;
import com.teco.market.like.repository.LikeRepository;
import com.teco.market.post.Post;
import com.teco.market.post.repository.PostRepository;
import com.teco.market.post.web.PostDetailResponse;
import com.teco.market.post.web.PostResponse;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Transactional(readOnly = true)
@Service
public class PostQueryService {
    private final PostRepository postRepository;
    private final LikeRepository likeRepository;
    private final CommentRepository commentRepository;

    public PostDetailResponse findPostDetailById(Long id) {
        Post post = postRepository.findById(id)
            .orElseThrow(NotFoundPostException::new);
        Long count = likeRepository.findCountByPostId(post.getId());
        List<Comment> comments = commentRepository.findByPostId(id);
        return PostDetailResponse.of(post, count, comments);
    }

    public List<PostResponse> findRepresentativePosts() {
        return postRepository.findRepresentativePosts();
    }

    public Page<PostResponse> findAllWithPaging(Pageable pageable) {
        return postRepository.findPosts(pageable);
    }
}
