package com.teco.market.slack;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.teco.market.member.domain.Member;
import com.teco.market.slack.notify.SlackMessageRequest;
import com.teco.market.support.LoginMember;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class SlackController {
    private final SlackService slackService;

    @PostMapping("/notify")
    public ResponseEntity<Void> send(@RequestBody SlackMessageRequest request, @LoginMember Member member) {
        slackService.sendMessage(request, member);
        return ResponseEntity.ok().build();
    }
}
