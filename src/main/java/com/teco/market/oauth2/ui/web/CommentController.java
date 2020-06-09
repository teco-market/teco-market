package com.teco.market.oauth2.ui.web;

import static org.springframework.http.ResponseEntity.*;

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
import org.springframework.web.bind.annotation.RestController;

import com.teco.market.domain.comment.MyCommentResponse;
import com.teco.market.domain.comment.service.CommentQueryService;
import com.teco.market.domain.comment.service.CommentService;
import com.teco.market.domain.comment.service.CommentUpdateRequest;
import com.teco.market.domain.member.Member;
import com.teco.market.domain.member.Role;
import com.teco.market.web.dto.CommentRequest;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
public class CommentController {
    private final CommentService commentService;
    private final CommentQueryService queryService;

    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<Void> create(@Valid @RequestBody CommentRequest commentRequest,
        @LoginMember Member member, @PathVariable Long postId) {
        commentService.save(postId, commentRequest, member);
        return status(HttpStatus.CREATED).build();
    }

    @PutMapping("/comments/{id}")
    public ResponseEntity<Void> update(@Valid @RequestBody CommentUpdateRequest request,
        @LoginMember Member member, @PathVariable Long id) {
        commentService.update(id, member, request);
        return ok().build();
    }


    @DeleteMapping("/comments/{id}")
    public ResponseEntity<Void> delete(@LoginMember Member member, @PathVariable Long id) {
        commentService.delete(id, member);
        return noContent().build();
    }

    @GetMapping("/me/comments")
    public ResponseEntity<Page<MyCommentResponse>> myComments(@LoginMember Member member, Pageable pageable) {
        Page<MyCommentResponse> myComments = queryService.findMyComments(member.getId(), pageable);
        return ok(myComments);
    }
}
