package com.teco.market.member;

import org.springframework.stereotype.Service;

import com.teco.market.generation.Generation;
import com.teco.market.generation.GenerationRepository;
import com.teco.market.common.exception.notfound.NotFoundGenerationException;
import com.teco.market.common.exception.notfound.NotFoundMemberException;
import com.teco.market.member.web.MemberUpdateRequest;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final GenerationRepository generationRepository;

    public Member findMemberById(Long id) {
        return memberRepository.findById(id).orElseThrow(NotFoundMemberException::new);
    }

    public void update(Member member, MemberUpdateRequest request) {
        Generation findGeneration = generationRepository.findById(request.getGenerationId())
            .orElseThrow(NotFoundGenerationException::new);
        member.setRequiredInfo(request.getNickname(), findGeneration);
        member.changeRole();
    }
}
