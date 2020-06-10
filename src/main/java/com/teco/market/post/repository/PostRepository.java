package com.teco.market.post.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teco.market.post.Post;

public interface PostRepository extends JpaRepository<Post, Long>, PostCustomRepository{
}
