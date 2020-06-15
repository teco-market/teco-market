package com.teco.market.slack.notify;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SlackMessageAttachment {
    private String color;
    private String pretext;
    private String title;
    private String titleLink;
    private String text;
}
