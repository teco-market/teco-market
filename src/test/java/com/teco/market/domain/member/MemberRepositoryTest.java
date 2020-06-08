package com.teco.market.domain.member;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.teco.market.oauth2.ui.user.PlatformType;

@DataJpaTest
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @DisplayName("플랫폼 정보로 회원을 조회한다")
    @Test
    void findByPlatformIdAndPlatformType() {
        Member member = memberRepository.save(new Member("1", PlatformType.GOOGLE, "kim","kim@gmail.com",Role.GUEST));
        Member findMember = memberRepository.findByPlatformIdAndPlatformType("1", PlatformType.GOOGLE).orElse(null);
        assertThat(member).isEqualTo(findMember);
    }

    @DisplayName("존재하지 않는 회원을 조회한다")
    @Test
    void findByPlatformIdAndPlatformTypeNotFound() {
        assertThat(
            memberRepository.findByPlatformIdAndPlatformType("1", PlatformType.GOOGLE).isPresent()
        ).isFalse();
    }
}