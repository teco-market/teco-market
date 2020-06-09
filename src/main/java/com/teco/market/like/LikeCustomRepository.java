package com.teco.market.like;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.teco.market.member.Member;

@Repository
public interface LikeCustomRepository {

    Page<MyLikeResponse> findMyLikes(Member member, Pageable pageable);
}
