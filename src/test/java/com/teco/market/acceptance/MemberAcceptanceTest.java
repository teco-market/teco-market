package com.teco.market.acceptance;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.DynamicTest.*;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import com.teco.market.TestUtil;
import com.teco.market.member.web.MemberResponse;
import com.teco.market.member.web.MemberUpdateRequest;

@Transactional
class MemberAcceptanceTest extends TestUtil {

    private String token;

    @TestFactory
    Stream<DynamicTest> manageMember() {

        return Stream.of((
            dynamicTest("구글 로그인 통해 토큰을 발급 받는다.", () -> {
                token = guestToken();
            })),
            dynamicTest("필수정보를 입력하면 일반 사용자가 된다.", () -> {
                setRequiredInfo(token);
            }));
    }

    private void setRequiredInfo(String token) throws Exception {
        requestRequiredInfo(token, "호돌", 1L);
        MemberResponse findMember = requestFindMember(token);
        assertThat(findMember.getGeneration()).isEqualTo(SECOND_GENERATION_NAME);
        assertThat(findMember.getNickname()).isEqualTo("호돌");
        assertThat(findMember.getRole()).isEqualTo("USER");
    }

    private void requestRequiredInfo(String token, String nickname, Long generationId) throws Exception {
        MemberUpdateRequest content = new MemberUpdateRequest(nickname, generationId);
        super.mockMvc.perform(put("/me")
            .content(super.objectMapper.writeValueAsString(content))
            .contentType(MediaType.APPLICATION_JSON)
            .header(AUTHORIZATION, TOKEN_TYPE, token)
        )
            .andExpect(status().isOk());
    }

    private MemberResponse requestFindMember(String token) throws Exception {
        MvcResult mvcResult = super.mockMvc.perform(get("/me")
            .header(AUTHORIZATION, TOKEN_TYPE, token)
        )
            .andExpect(status().isOk())
            .andReturn();
        String result = mvcResult.getResponse().getContentAsString();
        return super.objectMapper.readValue(result, MemberResponse.class);
    }
}