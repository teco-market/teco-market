package com.teco.market.domain.image;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import com.teco.market.domain.post.Post;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Thumbnail {
    @Id
    @GeneratedValue
    @Column(name = "post_id")
    private Long id;

    @PrimaryKeyJoinColumn(name = "post_id")
    @OneToOne
    private Post post;
    
    private String url;

    public Thumbnail(String url) {
        this.url = url;
    }
}
