package com.teco.market.member.domain;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.teco.market.member.MemberFixture;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MemberRepositoryTest {

    @Autowired MemberRepository memberRepository;

    @BeforeEach
    void setUp() {
        memberRepository.deleteAll();
    }

    @DisplayName("카카오 아이디를 통해 회원을 조회할 수 있다.")
    @Test
    void findByKakaoId() {
        Member savedMember = memberRepository.save(MemberFixture.createGuestWithId(1L));
        Optional<Member> findMember = memberRepository.findByKakaoId(savedMember.getKakaoId());

        assertAll(
            () -> assertThat(findMember.isPresent()).isTrue(),
            () -> assertThat(savedMember).isEqualToComparingFieldByField(findMember.get())
        );
    }
}