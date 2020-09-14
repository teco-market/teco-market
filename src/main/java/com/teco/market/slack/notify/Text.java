package com.teco.market.slack.notify;

import com.teco.market.member.domain.Member;
import com.teco.market.member.web.MemberResponse;
import com.teco.market.post.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Text {
    private static final String MARK_DOWN = "mrkdwn";
    private static final String IMAGE = "image";

    private String type;
    private String text;

    public Text(Member sender, MemberResponse receiver, Post post) {
        this.type = MARK_DOWN;
        this.text = createMessage(sender, receiver, post);
    }

    public Text(String text) {
        this.type = MARK_DOWN;
        this.text = text;
    }

    private String createMessage(Member sender, MemberResponse receiver, Post post) {
        StringBuilder sb = new StringBuilder();
        sb.append(sender.getNickname())
            .append("님께서 ")
            .append(receiver.getNickname())
            .append("님이 작성하신 ")
            .append(post.getTitle())
            .append("에 관심이 있습니다.");
        return sb.toString();
    }
}
