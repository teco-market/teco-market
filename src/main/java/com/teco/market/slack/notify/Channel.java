package com.teco.market.slack.notify;

import lombok.Getter;

@Getter
public enum Channel {
    INCOMING("https://hooks.slack.com/services/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", "프로젝트-레벨3");

    private final String url;
    private final String channel;

    Channel(String url, String channel) {
        this.url = url;
        this.channel = channel;
    }
}
