package com.teco.market.slack.notify;

import java.util.List;

import com.teco.market.member.Member;
import com.teco.market.post.Post;

public class Sections {
    private List<Section> sections;
    private Accessory accessory;

    public void addMessage(Member sender, Member receiver, Post post) {
        this.sections.add(new Section(sender, receiver, post));
    }

    public void addImage(Post post) {
        this.accessory = Accessory.create(post);
    }
}
