package com.teco.market.member;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.teco.market.common.exception.notfound.NotFoundGenerationException;
import com.teco.market.generation.GenerationRepository;
import com.teco.market.member.web.MemberUpdateRequest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @MockBean
    GenerationRepository generationRepository;

    @Test
    void findByIdException() {
        Member member = new Member();
        given(generationRepository.findById(any())).willThrow(NotFoundGenerationException.class);
        assertThatThrownBy(() -> {
            memberService.update(member, new MemberUpdateRequest());
        }).isInstanceOf(NotFoundGenerationException.class);
    }
}