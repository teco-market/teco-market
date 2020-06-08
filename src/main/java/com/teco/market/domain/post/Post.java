package com.teco.market.domain.post;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.teco.market.domain.BaseEntity;
import com.teco.market.domain.category.Category;
import com.teco.market.domain.image.Photo;
import com.teco.market.domain.image.Thumbnail;
import com.teco.market.domain.member.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Post extends BaseEntity {
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToOne(mappedBy = "post")
    private Thumbnail thumbnail;

    private BigDecimal price;

    @ElementCollection
    @CollectionTable(name = "photo", joinColumns = @JoinColumn(name = "post_id"))
    private List<Photo> photos = new ArrayList<>();

    @Lob
    private String content;

    @Builder
    public Post(String title, Member member, Category category, Thumbnail thumbnail, List<String> photos,
        BigDecimal price, String content) {
        this.title = title;
        this.member = member;
        //category 저장 후
        this.category = category;
        //thumbnail 저장 후 // 아닐수도?
        this.thumbnail = thumbnail;
        this.photos = photos.stream().map(Photo::new).collect(Collectors.toList());
        this.price = price;
        this.content = content;
    }
}
