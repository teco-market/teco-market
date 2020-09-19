package com.teco.market.search;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teco.market.post.web.PagedPostResponses;
import com.teco.market.post.web.PostResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class SearchController {
    private final SearchQueryService searchService;

    @GetMapping("/search")
    public ResponseEntity search(SearchCondition searchCondition, Pageable pageable) {
        Page<PostResponse> posts = searchService.search(searchCondition, pageable);

        return ResponseEntity.ok(PagedPostResponses.of(posts));
    }
}
