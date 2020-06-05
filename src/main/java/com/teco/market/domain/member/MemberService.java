package com.teco.market.domain.member;

import org.springframework.stereotype.Service;

import com.teco.market.exception.NotFoundMemberException;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public Member findMemberById(Long id) {
        return memberRepository.findById(id).orElseThrow(NotFoundMemberException::new);
    }
}
