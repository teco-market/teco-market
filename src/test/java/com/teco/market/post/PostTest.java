package com.teco.market.post;

import static org.assertj.core.api.Assertions.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.teco.market.member.Member;
import com.teco.market.member.Role;
import com.teco.market.oauth2.user.PlatformType;

@ExtendWith(SpringExtension.class)
class PostTest {
    private static final List<String> TEST_PHOTO_URL = Arrays.asList("EXAMPLE");

    @Test
    void updateTest() {
        Post post = new Post();
        post.changePost("aaa", BigDecimal.valueOf(200), "333");
        assertThat(post.getTitle()).isEqualTo("aaa");
        assertThat(post.getPrice()).isEqualTo(BigDecimal.valueOf(200));
        assertThat(post.getContent()).isEqualTo("333");
    }

    @Test
    void nullIsWrittenBy() {
        Member member = new Member(1L, null, null, "1", PlatformType.GOOGLE, "k", "k", Role.GUEST);
        Post post = new Post();
        assertThatThrownBy(() -> {
            post.isNotWrittenBy(member);
        }).isInstanceOf(NullPointerException.class);
    }

    @Test
    void isWrittenBy() {
        Member member = new Member(1L, null, null, "1", PlatformType.GOOGLE, "k", "k", Role.GUEST);
        Post post = Post.builder()
            .member(member)
            .photos(TEST_PHOTO_URL)
            .build();
        assertThat(post.isWrittenBy(member)).isTrue();
        assertThat(post.isNotWrittenBy(member)).isFalse();
    }
}