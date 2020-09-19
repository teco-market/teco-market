package com.teco.market.search;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.teco.market.post.web.PostResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class SearchQueryService {
    private final SearchRepository searchRepository;

    public Page<PostResponse> search(SearchCondition searchCondition, Pageable pageable) {
        return searchRepository.search(searchCondition, pageable);
    }
}
