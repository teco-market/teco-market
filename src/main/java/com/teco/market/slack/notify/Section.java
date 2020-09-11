package com.teco.market.slack.notify;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.teco.market.member.Member;
import com.teco.market.member.web.MemberResponse;
import com.teco.market.post.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Section {
    private static final String BASE_TITLE = "⭐️관심 요청⭐️️";
    private static final String SECTION = "section";

    private String type;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String block_id;
    private Text text;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Accessory accessory;

    public Section(Member sender, MemberResponse receiver, Post post) {
        this.type = SECTION;
        this.block_id = String.valueOf((int)(Math.random() * 1000) + 10);
        this.text = new Text(sender, receiver, post);
    }

    @Builder
    public Section(String type, Text text, Accessory accessory) {
        this.type = type;
        this.text = text;
        this.accessory = accessory;
    }

    public static Section createBasicMessageInfo() {
        return Section.builder()
            .type(SECTION)
            .text(new Text(BASE_TITLE))
            .build();
    }

    public void addAccessory(Post post) {
        this.accessory = Accessory.create(post);
    }
}
