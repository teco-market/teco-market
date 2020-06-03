package com.teco.market.post;

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

import com.teco.market.category.Category;
import com.teco.market.comment.Comment;
import com.teco.market.image.Photo;
import com.teco.market.image.Thumbnail;
import com.teco.market.like.Like;
import com.teco.market.member.Member;

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

    @CollectionTable(name = "photo", joinColumns = @JoinColumn(name = "post_id"))
    private Thumbnail thumbnail;

    @ElementCollection
    @CollectionTable(name = "photo", joinColumns = @JoinColumn(name = "post_id"))
    private List<Photo> photos = new ArrayList<>();

    @Lob
    private String content;
}
