package com.teco.market.slack.notify;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SlackMessageRequest {
    private Long receiver;
    private Long post;
}
