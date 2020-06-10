package com.teco.market.like.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.teco.market.like.repository.LikeRepository;
import com.teco.market.like.web.MyLikeResponse;
import com.teco.market.member.Member;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class LikeQueryService {
    private final LikeRepository likeRepository;

    public Page<MyLikeResponse> findMyLikes(Member member, Pageable pageable) {
        return likeRepository.findMyLikes(member, pageable);
    }
}