package com.teco.market.web;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teco.market.domain.member.Member;
import com.teco.market.domain.member.MemberService;
import com.teco.market.oauth2.ui.web.LoginMember;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RequestMapping("/me")
@RestController
public class MemberController {
    private final MemberService memberService;

    @PutMapping
    public ResponseEntity<Void> updateInfo(@LoginMember Member member, @Valid @RequestBody MemberUpdateRequest request) {
        memberService.update(member, request);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<MemberResponse> find(@LoginMember Member member) {
        return ResponseEntity.ok(MemberResponse.of(member));
    }
}
