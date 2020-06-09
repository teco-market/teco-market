package com.teco.market.post;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.teco.market.web.dto.PostResponse;

public interface PostCustomRepository {
    List<PostResponse> findRepresentativePosts();

    Page<PostResponse> findPosts(Pageable pageable);
}
