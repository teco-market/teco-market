package com.teco.market.post.repository;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import com.teco.market.category.Category;
import com.teco.market.member.domain.Member;
import com.teco.market.post.Post;

public class PostFixture {
    public static final String TITLE = "TTTITLE";
    public static final String CONTENT = "CCONTENT";
    public static final String THUMBNAIL_URL = "ASDASDDD@AASSD";
    public static final BigDecimal PRICE = BigDecimal.valueOf(100);
    public static final List<String> PHOTOS = Arrays.asList("AA", "BB", "CCC");

    public static Post createWithWithOut(Member member, Category category) {

        return Post.builder()
            .title(TITLE)
            .content(CONTENT)
            .member(member)
            .thumbnail(THUMBNAIL_URL)
            .price(PRICE)
            .category(category)
            .photos(PHOTOS)
            .build();
    }
}
