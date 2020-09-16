package com.teco.market.member.application;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.teco.market.generation.GenerationFixture;
import com.teco.market.generation.GenerationRepository;
import com.teco.market.member.MemberFixture;
import com.teco.market.member.domain.Member;
import com.teco.market.member.domain.MemberRepository;
import com.teco.market.member.web.MemberResponse;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {
    MemberService memberService;

    @Mock MemberRepository memberRepository;
    @Mock GenerationRepository generationRepository;

    @BeforeEach
    void setUp() {
        memberService = new MemberService(memberRepository, generationRepository);
    }

    @DisplayName("회원을 정상적으로 생성, 반환한다.")
    @Test
    void create() {
        Member savedMember = MemberFixture.createGuestWithId(1L);
        when(memberRepository.save(any())).thenReturn(savedMember);

        MemberResponse response = memberService.createMember(MemberFixture.createMemberRequest());

        assertThat(savedMember.getId()).isEqualTo(response.getId());
    }

    @DisplayName("회원을 정상적으로 찾을 수 있다.")
    @Test
    void retrieve() {
        Optional<Member> expected = Optional.of(MemberFixture.createGuestWithId(1L));
        when(memberRepository.findById(anyLong())).thenReturn(expected);

        MemberResponse memberResponse = memberService.retrieveById(1L);

        assertThat(memberResponse.getId()).isEqualTo(expected.get().getId());
    }

    @DisplayName("카카오 아이디로 회원을 찾을 수 있다.")
    @Test
    void findByKakaoId() {
        Optional<Member> expected = Optional.of(MemberFixture.createGuestWithId(1L));
        when(memberRepository.findByKakaoId(anyLong())).thenReturn(expected);

        Member response = memberService.findByKakaoId(1L);

        assertThat(expected.get()).isEqualToComparingFieldByField(response);
    }

    @DisplayName("카카오 아이디로 회원을 조회할 수 있다.")
    @Test
    void existsByKakaoId() {
        when(memberRepository.findByKakaoId(anyLong())).thenReturn(Optional.of(MemberFixture.createGuestWithId(1L)));

        assertThat(memberService.existsByKakaoId(1L)).isTrue();
    }

    @DisplayName("카카오 아이디로 존재하는 회원인지 확인할 수 있다.")
    @Test
    void notExistsByKakaoId() {
        when(memberRepository.findByKakaoId(anyLong())).thenReturn(Optional.empty());

        assertThat(memberService.existsByKakaoId(1L)).isFalse();
    }

    @DisplayName("회원의 정보를 업데이트 할 수 있다.")
    @Test
    void update() {
        when(memberRepository.findById(any())).thenReturn(Optional.of(MemberFixture.createGuestWithId(1L)));
        memberService.update(1L, MemberFixture.createUpdateRequest());
    }

    @DisplayName("회원의 필수정보를 입력받아 수정한다.")
    @Test
    void updateRequriedInfo() {
        when(memberRepository.findById(any())).thenReturn(Optional.of(MemberFixture.createGuestWithId(1L)));
        when(generationRepository.findById(any())).thenReturn(Optional.of(GenerationFixture.createWithId(1L)));

        memberService.updateRequiredInfo(1L, MemberFixture.createRequiredInfoRequest());
    }

    @DisplayName("회원을 삭제한다.")
    @Test
    void delete() {
        memberService.deleteById(1L);

        verify(memberRepository).deleteById(1L);
    }
}