package com.teco.market.slack;

import org.springframework.stereotype.Service;

import com.teco.market.member.application.MemberService;
import com.teco.market.member.domain.Member;
import com.teco.market.member.web.MemberResponse;
import com.teco.market.post.Post;
import com.teco.market.post.service.PostService;
import com.teco.market.slack.notify.Channel;
import com.teco.market.slack.notify.SlackMessageRequest;
import com.teco.market.slack.notify.SlackNotifier;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class SlackService {
    private final SlackNotifier notifier;
    private final MemberService memberService;
    private final PostService postService;

    public void sendMessage(SlackMessageRequest request, Member sender) {
        MemberResponse receiver = memberService.retrieveById(request.getReceiver());
        Post post = postService.findById(request.getPost());
        notifier.notify(Channel.INCOMING, sender, receiver, post);
    }
}
