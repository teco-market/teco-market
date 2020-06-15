package com.teco.market.slack.notify;

import com.teco.market.member.Member;
import com.teco.market.post.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Section {
    private static final String SECTION = "section";

    private String type;
    private Text text;

    public Section(Member sender, Member receiver, Post post) {
        this.type = SECTION;
        this.text = new Text(sender, receiver, post);
    }
}
