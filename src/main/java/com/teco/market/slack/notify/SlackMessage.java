package com.teco.market.slack.notify;

import static com.google.common.collect.Lists.*;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SlackMessage {
    private String text;
    private String channel;
    private List<SlackMessageAttachment> attachmentList = newArrayList();

    void addAttachment(SlackMessageAttachment attachment) {
        this.attachmentList.add(attachment);
    }
}
