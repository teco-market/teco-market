package com.teco.market.post.repository;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import com.teco.market.common.config.QueryDslConfiguration;
import com.teco.market.image.Photo;
import com.teco.market.post.Post;

@DataJpaTest
@Import(QueryDslConfiguration.class)
class PostRepositoryTest {
    @Autowired PostRepository postRepository;
    @Autowired EntityManager entityManager;

    @Test
    void create() {
        Post post = Post.builder()
            .title("aaa")
            .content("aaa")
            .price(BigDecimal.valueOf(111))
            .category(null)
            .member(null)
            .thumbnail("aaa")
            .photos(Arrays.asList("aaa", "bbb"))
            .build();

        Post post2 = Post.builder()
            .title("aaa")
            .content("aaa")
            .price(BigDecimal.valueOf(111))
            .category(null)
            .member(null)
            .thumbnail("aaa")
            .photos(Arrays.asList("ccc", "ddd"))
            .build();

        Post a = postRepository.save(post);
        Post b = postRepository.save(post2);
        entityManager.flush();
        entityManager.clear();

        List<Post> all = postRepository.findAll();
        System.out.println(all.size());
        for (Post post1 : all) {
            List<Photo> photos = post1.getPhotos();
            System.out.println(photos);
        }
    }
}