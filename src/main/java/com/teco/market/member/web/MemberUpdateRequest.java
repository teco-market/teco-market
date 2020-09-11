package com.teco.market.member.web;

import com.teco.market.member.Member;
import com.teco.market.member.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberUpdateRequest {
    private String name;
    private String email;
    private String nickname;

    @Builder
    public MemberUpdateRequest(String name, String email, String nickname) {
        this.name = name;
        this.email = email;
        this.nickname = nickname;
    }
}
