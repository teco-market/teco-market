package com.teco.market.post;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.teco.market.BaseEntity;
import com.teco.market.category.Category;
import com.teco.market.image.Photo;
import com.teco.market.image.Thumbnail;
import com.teco.market.member.Member;
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
        this.category = category;
        this.thumbnail = thumbnail;
        this.photos = photos.stream().map(Photo::new).collect(Collectors.toList());
        this.price = price;
        this.content = content;
    }

    public void changePost(String title, BigDecimal price, String content) {
        this.title = title;
        this.price = price;
        this.content = content;
    }

    public boolean isWrittenBy(Member member) {
        Objects.requireNonNull(this.member, "작성자가 없는 게시물입니다.");
        Objects.requireNonNull(member, "인자로 들어온 member가 null입니다.");
        return Objects.equals(this.member.getId(), member.getId());
    }

    public boolean isNotWrittenBy(Member member) {
        return !isWrittenBy(member);
    }
}
