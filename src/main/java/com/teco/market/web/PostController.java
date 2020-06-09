package com.teco.market.web;

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
import org.springframework.web.bind.annotation.RestController;

import com.teco.market.domain.member.Member;
import com.teco.market.domain.member.Role;
import com.teco.market.domain.post.service.PostQueryService;
import com.teco.market.domain.post.service.PostRequest;
import com.teco.market.domain.post.service.PostService;
import com.teco.market.oauth2.ui.web.AllowRole;
import com.teco.market.oauth2.ui.web.LoginMember;
import com.teco.market.web.dto.PostDetailResponse;
import com.teco.market.web.dto.PostResponse;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;
    private final PostQueryService queryService;

    @AllowRole(roles = {Role.USER, Role.ADMIN})
    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody PostRequest request, @LoginMember Member member) {
        postService.save(request, member);
        return status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDetailResponse> findById(@PathVariable Long id) {
        PostDetailResponse postDetail = queryService.findPostDetailById(id);
        return ok(postDetail);
    }

    @AllowRole(roles = {Role.USER, Role.ADMIN})
    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@Valid @RequestBody PostUpdateRequest request,
        @PathVariable Long id, @LoginMember Member member) {
        postService.update(request, id, member);
        return ok().build();
    }

    @AllowRole(roles = {Role.USER, Role.ADMIN})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id, @LoginMember Member member) {
        postService.deleteById(id, member);
        return noContent().build();
    }

    @GetMapping("/representative")
    public ResponseEntity<List<PostResponse>> findRepresentativePosts() {
        List<PostResponse> representativePosts = queryService.findRepresentativePosts();
        return ok(representativePosts);
    }

    @GetMapping
    public ResponseEntity<Page<PostResponse>> findAll(Pageable pageable) {
        Page<PostResponse> posts = queryService.findAllWithPaging(pageable);
        return ok(posts);
    }
}
