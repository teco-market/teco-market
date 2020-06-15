package com.teco.market.slack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.teco.market.slack.notify.Channel;
import com.teco.market.slack.notify.SlackMessageAttachment;
import com.teco.market.slack.notify.SlackNotifier;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
public class SlackController {
    private final SlackNotifier notifier;

    @PostMapping("/notify")
    public ResponseEntity<Void> send(@RequestBody SlackMessageAttachment message) {
        notifier.notify(Channel.INCOMING, message);
        return ResponseEntity.ok().build();
    }
}
