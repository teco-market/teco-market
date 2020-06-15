package com.teco.market.slack.notify;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Slf4j
@Component
public class SlackNotifier {
    private final RestTemplate restTemplate;

    public void notify(Channel channel, SlackMessageAttachment message) {
        SlackMessage slackMessage = SlackMessage.builder()
            .channel(channel.getChannel())
            .attachmentList(Lists.newArrayList(message))
            .build();

        restTemplate.postForEntity(channel.getUrl(), slackMessage, String.class);
    }
}
