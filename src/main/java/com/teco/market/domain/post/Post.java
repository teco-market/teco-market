package com.teco.market.domain.post;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.teco.market.domain.category.Category;
import com.teco.market.domain.comment.Comment;
import com.teco.market.domain.image.Photo;
import com.teco.market.domain.image.Thumbnail;
import com.teco.market.domain.like.Like;
import com.teco.market.domain.member.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Post {
    @Id
    @GeneratedValue
    private Long id;

    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JoinColumn(name = "post_id")
    private List<Like> likes = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "post_id")
    private List<Comment> comments;

    // TODO: 2020/06/03 ==========
    @CollectionTable(name = "photo", joinColumns = @JoinColumn(name = "post_id"))
    private Thumbnail thumbnail;

    @ElementCollection
    @CollectionTable(name = "photo", joinColumns = @JoinColumn(name = "post_id"))
    private List<Photo> photos = new ArrayList<>();

    @Lob
    private String content;
}
