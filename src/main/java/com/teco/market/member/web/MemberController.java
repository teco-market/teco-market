package com.teco.market.member.web;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teco.market.member.application.MemberService;
import com.teco.market.member.domain.Member;
import com.teco.market.support.LoginMember;
import com.teco.market.support.annotation.Permission;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Permission(target = "USER")
@RequestMapping("/api/members")
@RestController
public class MemberController {
    private final MemberService memberService;

    @Permission(target = "NONE")
    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody MemberCreateRequest request) {
        MemberResponse response = memberService.createMember(request);

        return ResponseEntity.created(URI.create("/api/members/" + response.getId())).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MemberResponse> find(@PathVariable("id") Long id) {
        MemberResponse response = memberService.retrieveById(id);

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<MemberResponse> find(@LoginMember Member member) {

        MemberResponse body = MemberResponse.withGeneration(member);
        return ResponseEntity.ok(body);
    }

    @PutMapping
    public ResponseEntity<MemberResponse> update(@LoginMember Member member,
        @Valid @RequestBody MemberUpdateRequest request) {
        memberService.update(member.getId(), request);

        return ResponseEntity.noContent().build();
    }

    @Permission(target = "GUEST")
    @PatchMapping
    public ResponseEntity<Void> updateInfo(@LoginMember Member member,
        @Valid @RequestBody MemberRequiredUpdateRequest request) {
        memberService.updateRequiredInfo(member.getId(), request);

        return ResponseEntity.noContent().build();
    }

    @Permission(target = "GUEST")
    @PatchMapping("/admin/permission")
    ResponseEntity<Void> change(@LoginMember Member member, @RequestBody MemberRequiredUpdateRequest request) {
        memberService.admin(member);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@LoginMember Member member) {
        memberService.deleteById(member.getId());

        return ResponseEntity.noContent().build();
    }

    @Permission(target = "ADMIN")
    @DeleteMapping("/all")
    public ResponseEntity<Void> deleteAll() {
        memberService.deleteAll();

        return ResponseEntity.noContent().build();
    }
}
