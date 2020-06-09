package com.teco.market.domain.post;

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
        this.category = category;
        // TODO: 2020/06/09 엔티티로 저장되는지, 값객체로써 처리되는지 확인해야함.
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
        return Objects.equals(this.member.getId(), member.getId());
    }

    public boolean isNotWrittenBy(Member member) {
        return !isWrittenBy(member);
    }
}
