package com.teco.market.slack.notify;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SlackMessageRequest {
    private Long receiver;
    private Long post;
}
