package com.teco.market.like.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.teco.market.like.Like;
import com.teco.market.like.repository.LikeRepository;
import com.teco.market.member.Member;
import com.teco.market.post.Post;
import com.teco.market.post.repository.PostRepository;
import com.teco.market.common.exception.notfound.NotFoundPostException;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Transactional
@Service
public class LikeService {
    private final LikeRepository likeRepository;
    private final PostRepository postRepository;

    public void plus(Long id, Member member) {
        Post post = postRepository.findById(id)
            .orElseThrow(NotFoundPostException::new);
        Like like = Like.builder()
            .post(post)
            .member(member)
            .build();
        likeRepository.save(like);
    }

    public void minus(Long id, Member member) {
        Post post = postRepository.findById(id)
            .orElseThrow(NotFoundPostException::new);
        likeRepository.deleteByPostAndMember(post, member);
    }

    public Long findLikeCount(Long id) {
        return likeRepository.findCountByPostId(id);
    }
}
