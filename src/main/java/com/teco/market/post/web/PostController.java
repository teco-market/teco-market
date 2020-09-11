package com.teco.market.post.web;

import static org.springframework.http.ResponseEntity.*;

import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.teco.market.member.Member;
import com.teco.market.post.service.PostQueryService;
import com.teco.market.post.service.PostService;
import com.teco.market.support.annotation.LoginMember;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;
    private final PostQueryService queryService;

    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody PostRequest request,
        @RequestParam("data") List<MultipartFile> files, @LoginMember Member member) {
        postService.save(request, files, member);
        return status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDetailResponse> findById(@PathVariable("id") Long id) {
        PostDetailResponse postDetail = queryService.findPostDetailById(id);
        return ok(postDetail);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@Valid @RequestBody PostUpdateRequest request,
        @PathVariable("id") Long id, @LoginMember Member member) {
        postService.update(request, id, member);
        return ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id, @LoginMember Member member) {
        postService.deleteById(id, member);
        return noContent().build();
    }

    @GetMapping("/representative")
    public ResponseEntity<PostResponses> findRepresentativePosts() {
        List<PostResponse> representativePosts = queryService.findRepresentativePosts();
        return ok(PostResponses.of(representativePosts));
    }

    @GetMapping
    public ResponseEntity<Page<PostResponse>> findAll(Pageable pageable) {
        Page<PostResponse> posts = queryService.findAllWithPaging(pageable);
        return ok(posts);
    }
}
