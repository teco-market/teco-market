package com.teco.market.member.web;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teco.market.member.Member;
import com.teco.market.member.MemberService;
import com.teco.market.oauth2.web.LoginMember;
import com.teco.market.oauth2.web.interceptor.Authorized;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RequestMapping("/me")
@RestController
public class MemberController {
    private final MemberService memberService;

    @Authorized
    @PutMapping
    public ResponseEntity<Void> updateInfo(@LoginMember Member member, @Valid @RequestBody MemberUpdateRequest request) {
        memberService.update(member, request);
        return ResponseEntity.ok().build();
    }

    @Authorized
    @GetMapping
    public ResponseEntity<MemberResponse> find(@LoginMember Member member) {
        return ResponseEntity.ok(MemberResponse.of(member));
    }
}