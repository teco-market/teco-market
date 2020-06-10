package com.teco.market.post.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.teco.market.post.web.PostResponse;

public interface PostCustomRepository {
    List<PostResponse> findRepresentativePosts();

    Page<PostResponse> findPosts(Pageable pageable);
}
