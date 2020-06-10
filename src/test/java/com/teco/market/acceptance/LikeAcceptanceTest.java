package com.teco.market.acceptance;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.DynamicTest.*;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.springframework.data.domain.Page;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.type.TypeReference;
import com.teco.market.TestUtil;
import com.teco.market.like.MyLikeResponse;
import com.teco.market.web.CustomPage;


public class LikeAcceptanceTest extends TestUtil {
    private String token;

    @Transactional
    @TestFactory
    Stream<DynamicTest> manageLike() {
        return Stream.of(
            dynamicTest("등록된 글에 좋아요를 할 수 있다.", () -> {
                token = userToken();
                createLikeToPost();
            }),
            dynamicTest("좋아요를 취소할 수 있다.", this::cancelLike),
            dynamicTest("특정한 글에 좋아요의 갯수를 찾을 수 있다.", () -> {
                createLikeToPost();
                findLikesByPost();
            }),
            dynamicTest("내가 좋아요한 글을 확인할 수 있다.", () -> {
                findMyLikes();
            })
        );
    }

    private void createLikeToPost() throws Exception {
        super.mockMvc.perform(post("/posts/1/likes")
            .header(AUTHORIZATION, TOKEN_TYPE, token)
        )
            .andExpect(status().isCreated());
    }

    private void cancelLike() throws Exception {
        super.mockMvc.perform(delete("/posts/1/likes")
            .header(AUTHORIZATION, TOKEN_TYPE, token)
        )
            .andExpect(status().isNoContent());
    }

    private void findLikesByPost() throws Exception {
        super.mockMvc.perform(get("/posts/1/likes"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.count").value(1L));
    }

    private void findMyLikes() throws Exception {
        MvcResult mvcResult = super.mockMvc.perform(get("/me/likes")
            .header(AUTHORIZATION, TOKEN_TYPE, token)
        )
            .andExpect(status().isOk())
            .andReturn();
        Page<MyLikeResponse> myLikeResponses = super.objectMapper.readValue(
            mvcResult.getResponse().getContentAsString(),
            new TypeReference<CustomPage<MyLikeResponse>>() {});
        assertThat(myLikeResponses.getTotalElements()).isEqualTo(1);
    }
}
