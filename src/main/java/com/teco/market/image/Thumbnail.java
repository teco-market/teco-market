package com.teco.market.image;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import com.teco.market.post.Post;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
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
