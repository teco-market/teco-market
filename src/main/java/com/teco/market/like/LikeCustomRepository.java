package com.teco.market.like;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.teco.market.member.Member;

public interface LikeCustomRepository {

    Page<MyLikeResponse> findMyLikes(Member member, Pageable pageable);
}
