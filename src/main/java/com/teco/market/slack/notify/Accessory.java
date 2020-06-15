package com.teco.market.slack.notify;

import com.teco.market.post.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Accessory {
    private static final String IMAGE = "image";
    private String type;
    private String imageUrl;
    private String altText;

    private Accessory(Post post) {
        this.type = IMAGE;
        this.imageUrl = post.getThumbnail().getUrl();
        this.altText = post.getTitle();
    }

    public static Accessory create(Post post) {
        return new Accessory(post);
    }
}
