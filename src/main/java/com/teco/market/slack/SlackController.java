package com.teco.market.slack;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.teco.market.member.Member;
import com.teco.market.oauth2.web.LoginMember;
import com.teco.market.oauth2.web.interceptor.Authorized;
import com.teco.market.slack.notify.SlackMessageRequest;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
public class SlackController {
    private final SlackService slackService;

    @Authorized
    @PostMapping("/notify")
    public ResponseEntity<Void> send(@RequestBody SlackMessageRequest request, @LoginMember Member member) {
        slackService.sendMessage(request, member);
        return ResponseEntity.ok().build();
    }
}
