package com.teco.market.slack.notify;

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
    private Sections blocks;

}
