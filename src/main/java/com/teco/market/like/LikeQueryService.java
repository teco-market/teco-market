package com.teco.market.like;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.teco.market.member.Member;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class LikeQueryService {
    private final LikeCustomRepository likeCustomRepository;

    public Page<MyLikeResponse> findMyLikes(Member member, Pageable pageable) {
        return likeCustomRepository.findMyLikes(member, pageable);
    }
}
