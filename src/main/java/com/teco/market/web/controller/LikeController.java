package com.teco.market.web.controller;

import static org.springframework.http.ResponseEntity.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teco.market.like.LikeQueryService;
import com.teco.market.like.LikeResponse;
import com.teco.market.like.LikeService;
import com.teco.market.like.MyLikeResponse;
import com.teco.market.member.Member;
import com.teco.market.oauth2.web.LoginMember;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
public class LikeController {
    private final LikeService likeService;
    private final LikeQueryService likeQueryService;

    @PostMapping("/post/{id}/like")
    public ResponseEntity<Void> create(@PathVariable("id") Long id, @LoginMember Member member) {
        likeService.plus(id, member);
        return status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/post/{id}/like")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id, @LoginMember Member member) {
        likeService.minus(id, member);
        return noContent().build();
    }

    @GetMapping("/post/{id}")
    public ResponseEntity<LikeResponse> findCount(@PathVariable("id") Long id) {
        Long count = likeService.findLikeCount(id);
        return ok(LikeResponse.of(count));
    }

    @GetMapping("/me/like")
    public ResponseEntity<Page<MyLikeResponse>> findMyLikes(@LoginMember Member member, Pageable pageable) {
        Page<MyLikeResponse> myLikes = likeQueryService.findMyLikes(member, pageable);
        return ok(myLikes);
    }
}