package com.teco.market.domain.comment;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CommentRepository extends JpaRepository<Comment, Long>, CommentCustomRepository {

    @Query("select c from Comment as c where c.post.id = :id")
    List<Comment> findByPostId(Long id);
}
