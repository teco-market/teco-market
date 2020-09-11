package com.teco.market.member;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.teco.market.generation.Generation;

class MemberTest {

    @DisplayName("정상적으로 필수정보 입력이 수행된다.(기수와 양방향 관계)")
    @Test
    void setRequiredInfo() {
        Member member = new Member();
        member.updateRequiredInfo("카일", new Generation("우테크루즈"));

        assertThat(member.getNickname()).isEqualTo("카일");
        assertThat(member.getGeneration().getAlias()).isEqualTo("우테크루즈");
        assertThat(member.getGeneration().getMembers().contains(member)).isTrue();
    }
}