package com.teco.market.slack.notify;

import java.util.List;

import com.google.common.collect.Lists;
import com.teco.market.member.Member;
import com.teco.market.post.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SlackMessage {
    private String channel;
    private List<Section> blocks = Lists.newArrayList();

    public SlackMessage(String channel) {
        blocks.add(Section.createBasicMessageInfo());
        this.channel = channel;
    }

    public void addMessage(Member sender, Member receiver, Post post) {
        Section section = new Section(sender, receiver, post);
        section.addAccessory(post);
        blocks.add(section);
    }
}
