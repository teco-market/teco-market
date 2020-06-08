package com.teco.market.web;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teco.market.domain.member.Member;
import com.teco.market.domain.member.Role;
import com.teco.market.domain.post.service.PostRequest;
import com.teco.market.domain.post.service.PostService;
import com.teco.market.oauth2.ui.web.AllowRole;
import com.teco.market.oauth2.ui.web.LoginMember;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/post")
public class PostController {
    private final PostService postService;

    @AllowRole(roles = {Role.USER, Role.ADMIN})
    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody PostRequest request, @LoginMember Member member) {
        postService.save(request, member);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
