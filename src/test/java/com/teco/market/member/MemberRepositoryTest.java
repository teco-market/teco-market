package com.teco.market.member;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.teco.market.oauth2.user.PlatformType;

@DataJpaTest
@ExtendWith(SpringExtension.class)
class MemberRepositoryTest {
    @Autowired
    MemberRepository memberRepository ;

    @Test
    void exists() {
        assertThat(memberRepository).isNotNull();
    }

    @Test
    void findByPlatformIdAndPlatformType() {
        Member member = memberRepository.save(new Member("1", PlatformType.GOOGLE, "kim","kim@gmail.com",Role.GUEST));
        Member findMember = memberRepository.findByPlatformIdAndPlatformType("1", PlatformType.GOOGLE).orElse(null);
        assertThat(member).isEqualTo(findMember);
    }

    @Test
    void findByPlatformIdAndPlatformTypeNotFound() {
        assertThat(
            memberRepository.findByPlatformIdAndPlatformType("1", PlatformType.GOOGLE).isPresent()
        ).isFalse();
    }

}