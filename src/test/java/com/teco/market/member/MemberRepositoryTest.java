package com.teco.market.member;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.teco.market.common.config.QueryDslConfiguration;
import com.teco.market.oauth2.user.PlatformType;

@DataJpaTest
@ExtendWith(SpringExtension.class)
@Import(QueryDslConfiguration.class)
class MemberRepositoryTest {
    @Autowired
    MemberRepository memberRepository;

    @Test
    void exists() {
        assertThat(memberRepository).isNotNull();
    }

    @Test
    void findByPlatformIdAndPlatformTypeNotFound() {
        assertThat(
            memberRepository.findByKakaoId("1", PlatformType.GOOGLE).isPresent()
        ).isTrue();
    }
}