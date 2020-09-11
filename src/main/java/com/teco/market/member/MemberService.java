package com.teco.market.member;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.teco.market.common.exception.notfound.NotFoundGenerationException;
import com.teco.market.common.exception.notfound.NotFoundMemberException;
import com.teco.market.generation.Generation;
import com.teco.market.generation.GenerationRepository;
import com.teco.market.member.web.MemberCreateRequest;
import com.teco.market.member.web.MemberResponse;
import com.teco.market.member.web.MemberUpdateRequest;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final GenerationRepository generationRepository;


    public MemberResponse createMember(MemberCreateRequest request) {
        Member requestMember = request.toMember();
        Member savedMember = memberRepository.save(requestMember);

        return MemberResponse.of(savedMember);
    }

    public Member findMemberById(Long id) {
        return memberRepository.findById(id)
            .orElseThrow(NotFoundMemberException::new);
    }

    public void update(Member member, MemberUpdateRequest request) {
        Generation findGeneration = generationRepository.findById(request.getGenerationId())
            .orElseThrow(NotFoundGenerationException::new);
        member.setRequiredInfo(request.getNickname(), findGeneration);
        member.changeRole();
    }

    public boolean existsByKakaoId(Long id) {
        Optional<Member> findMember = memberRepository.findByKakaoId(id);

        return findMember.isPresent();
    }

    public MemberResponse findByKakaoId(Long id) {
        Member findMember = memberRepository.findByKakaoId(id)
            .orElseThrow(NotFoundMemberException::new);

        return MemberResponse.of(findMember);
    }
}
