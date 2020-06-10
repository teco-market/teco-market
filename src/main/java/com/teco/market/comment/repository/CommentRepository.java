package com.teco.market.comment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.teco.market.comment.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long>, CommentCustomRepository {

    @Query("select c from Comment as c where c.post.id=:id")
    List<Comment> findByPostId(@Param("id") Long id);
}
