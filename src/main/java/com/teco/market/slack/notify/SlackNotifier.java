package com.teco.market.slack.notify;

import java.util.Arrays;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.teco.market.common.exception.InvalidSlackMessageException;
import com.teco.market.member.Member;
import com.teco.market.member.web.MemberResponse;
import com.teco.market.post.Post;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Slf4j
@Component
public class SlackNotifier {
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public void notify(Channel channel, Member sender, MemberResponse receiver, Post post) {
        SlackMessage slackMessage = new SlackMessage(channel.getChannel());
        slackMessage.addMessage(sender, receiver, post);

        send(channel.getUrl(), slackMessage);
    }

    private void send(String url, SlackMessage message) {
        try {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
            httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON_UTF8));
            String request = objectMapper.writeValueAsString(message);
            HttpEntity<String> entity = new HttpEntity<>(request, httpHeaders);

            restTemplate.postForEntity(url, entity, String.class);
        } catch (Exception e) {
            throw new InvalidSlackMessageException();
        }
    }
}
