package com.teco.market.slack.notify;

import org.springframework.boot.context.properties.EnableConfigurationProperties;

import lombok.Getter;

@Getter
public enum Channel {
    INCOMING(UrlInfo.INCOMING, "프로젝트-레벨3");

    private final String url;
    private final String channel;

    Channel(String url, String channel) {
        this.url = url;
        this.channel = channel;
    }
}
