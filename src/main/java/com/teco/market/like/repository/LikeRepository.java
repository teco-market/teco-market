package com.teco.market.like.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.teco.market.like.Like;
import com.teco.market.member.domain.Member;
import com.teco.market.post.Post;

public interface LikeRepository extends JpaRepository<Like, Long>, LikeCustomRepository {

    @Query("select count(l) from Like l where l.post.id=:id")
    Long findCountByPostId(@Param("id") Long id);

    void deleteByPostAndMember(@Param("post") Post post, @Param("member") Member member);
}
