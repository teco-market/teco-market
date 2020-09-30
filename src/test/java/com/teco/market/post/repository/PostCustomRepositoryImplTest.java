package com.teco.market.post.repository;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.teco.market.category.Category;
import com.teco.market.category.CategoryRepository;
import com.teco.market.common.config.QueryDslConfiguration;
import com.teco.market.member.MemberFixture;
import com.teco.market.member.domain.Member;
import com.teco.market.member.domain.MemberRepository;
import com.teco.market.post.web.PostResponse;

@DataJpaTest
@Import(QueryDslConfiguration.class)
class PostCustomRepositoryImplTest {
    @Autowired MemberRepository memberRepository;
    @Autowired PostRepository postRepository;
    @Autowired CategoryRepository categoryRepository;

    @BeforeEach
    void setUp() {
        Member member = memberRepository.save(MemberFixture.createUserWithId(null));
        Category category = categoryRepository.save(new Category("가전"));
        IntStream.range(0, 20)
            .forEachOrdered(i -> postRepository.save(PostFixture.createWithWithOut(member, category)));
    }

    @Test
    void findRepresentative() {
        List<PostResponse> representativePosts = postRepository.findRepresentativePosts();
        assertThat(representativePosts).hasSize(15);
    }

    @Test
    void pagedPost() {
        Page<PostResponse> posts = postRepository.findPosts(PageRequest.of(0, 3));

        assertAll(
            () -> assertThat(posts.getTotalElements()).isEqualTo(20),
            () -> assertThat(posts.getTotalPages()).isEqualTo(7),
            () -> assertThat(posts.getContent()).hasSize(3),
            () -> assertThat(posts.getContent().get(0).getPhotos()).hasSize(3)
        );
    }
}