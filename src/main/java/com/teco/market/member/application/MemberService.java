package com.teco.market.member.application;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.teco.market.common.exception.notfound.NotFoundGenerationException;
import com.teco.market.common.exception.notfound.NotFoundMemberException;
import com.teco.market.generation.Generation;
import com.teco.market.generation.GenerationRepository;
import com.teco.market.member.domain.Member;
import com.teco.market.member.domain.MemberRepository;
import com.teco.market.member.web.MemberCreateRequest;
import com.teco.market.member.web.MemberRequiredUpdateRequest;
import com.teco.market.member.web.MemberResponse;
import com.teco.market.member.web.MemberUpdateRequest;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional
@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final GenerationRepository generationRepository;

    public MemberResponse createMember(MemberCreateRequest request) {
        Member requestMember = request.toMember();
        Member savedMember = memberRepository.save(requestMember);

        return MemberResponse.of(savedMember);
    }

    @Transactional(readOnly = true)
    public MemberResponse retrieveById(Long id) {
        Member findMember = memberRepository.findById(id)
            .orElseThrow(NotFoundMemberException::new);

        return MemberResponse.of(findMember);
    }

    @Transactional(readOnly = true)
    public Member findByKakaoId(Long id) {

        return memberRepository.findByKakaoId(id)
            .orElseThrow(NotFoundMemberException::new);
    }

    @Transactional(readOnly = true)
    public boolean existsByKakaoId(Long id) {
        Optional<Member> findMember = memberRepository.findByKakaoId(id);

        return findMember.isPresent();
    }

    public void update(Long id, MemberUpdateRequest request) {
        Member findMember = memberRepository.findById(id)
            .orElseThrow(NotFoundMemberException::new);

        findMember.updateRequiredInfo(request.getName(), request.getNickname(), request.getEmail());
    }

    public void admin(Member member) {
        member.admin();
        memberRepository.save(member);
    }

    public void updateRequiredInfo(Long id, MemberRequiredUpdateRequest request) {
        Member findMember = memberRepository.findById(id)
            .orElseThrow(NotFoundMemberException::new);
        Generation findGeneration = generationRepository.findById(request.getGenerationId())
            .orElseThrow(NotFoundGenerationException::new);

        findMember.updateRequiredInfo(request.getNickname(), findGeneration);
    }

    public void deleteById(Long id) {
        memberRepository.deleteById(id);
    }

    public void deleteAll() {
        memberRepository.deleteAll();
    }
}
