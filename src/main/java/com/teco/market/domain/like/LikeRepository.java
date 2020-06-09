package com.teco.market.domain.like;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LikeRepository extends JpaRepository<Like, Long> {

    @Query("select count(l) from Like l")
    Long findCountByPostId(Long id);
}
