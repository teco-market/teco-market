package com.teco.market.slack.notify;

import com.teco.market.post.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Accessory {
    private static final String IMAGE = "image";

    private String type;
    private String image_url;
    private String alt_text;

    private Accessory(Post post) {
        this.type = IMAGE;
        this.image_url = post.getThumbnail().getUrl();
        this.alt_text = post.getTitle();
    }

    public static Accessory create(Post post) {
        return new Accessory(post);
    }
}
