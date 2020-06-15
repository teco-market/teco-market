package com.teco.market.acceptance;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.DynamicTest.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.util.stream.Stream;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.type.TypeReference;
import com.teco.market.TestUtil;
import com.teco.market.post.web.PostDetailResponse;
import com.teco.market.post.web.PostRequest;
import com.teco.market.post.web.PostResponse;
import com.teco.market.post.web.PostResponses;
import com.teco.market.post.web.PostUpdateRequest;
import com.teco.market.web.CustomPage;

@Transactional
class PostAcceptanceTest extends TestUtil {
    private String token;
    private PostDetailResponse postDetailResponse;

    @Test
    void imageUploadTest() throws Exception {
        String token = userToken();
        File target = new File("/Users/kimsiyoung/IdeaProjects/market/git_commit2.png");
        FileInputStream fileInputStream = new FileInputStream(target);
        MockMultipartFile file = new MockMultipartFile("data", "git_commit2.png", MediaType.IMAGE_PNG_VALUE, fileInputStream);
        PostRequest request = new PostRequest("TEST", 1L, 1000, "TEST_CONTENT");

        super.mockMvc.perform(multipart("/posts")
            .file(file)
            .content(super.objectMapper.writeValueAsString(request))
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .header(AUTHORIZATION, TOKEN_TYPE, token)
        )
            .andExpect(status().isCreated())
            .andDo(print());
    }

    @TestFactory
    Stream<DynamicTest> managePost() {
        return Stream.of(
            dynamicTest("글이 등록되어 있다.", () -> {
                token = userToken();
                requestEveryPosts();
            }),
            dynamicTest("특정한 글을 가져올 수 있다.", () -> {
                postDetailResponse = requestOnePost(1L);
            }),
            dynamicTest("가져온 글을 수정한다.", () -> {
                requestUpdate(token, postDetailResponse.getId());
            }),
            dynamicTest("대표 글들을 가져온다.", this::requestRepresentativePosts)
            ,
            dynamicTest("특정 글을 삭제할 수 있다.", () -> {
                requestDeletePost(postDetailResponse.getId(), token);
            })
        );
    }

    private void requestRepresentativePosts() throws Exception {
        MvcResult mvcResult = super.mockMvc.perform(get("/posts/representative"))
            .andExpect(status().isOk())
            .andReturn();
        PostResponses result = super.objectMapper.readValue(mvcResult.getResponse().getContentAsString(),
            PostResponses.class);
        assertThat(result.getResponses()).hasSize(2);
    }

    private void requestEveryPosts() throws Exception {
        MvcResult mvcResult = super.mockMvc.perform(get("/posts")
            .header(AUTHORIZATION, TOKEN_TYPE, token)
        )
            .andExpect(status().isOk())
            .andReturn();

        String contentAsString = mvcResult.getResponse().getContentAsString();
        Page<PostResponse> response = super.objectMapper.readValue(
            contentAsString, new TypeReference<CustomPage<PostResponse>>() {
            });
        assertThat(response.getContent()).hasSize(2);
    }

    private PostDetailResponse requestOnePost(Long id) throws Exception {
        MvcResult mvcResult = super.mockMvc.perform(get("/posts/" + id))
            .andExpect(status().isOk())
            .andReturn();
        PostDetailResponse postDetailResponse = super.objectMapper.readValue(
            mvcResult.getResponse().getContentAsString(), PostDetailResponse.class);
        assertThat(postDetailResponse).isNotNull();
        assertThat(postDetailResponse.getId()).isEqualTo(1);
        return postDetailResponse;
    }

    private void requestUpdate(String token, Long id) throws Exception {
        PostUpdateRequest request = new PostUpdateRequest("변경된 제목", BigDecimal.valueOf(500), "변경된 본문");
        super.mockMvc.perform(put("/posts/" + id)
            .header(AUTHORIZATION, TOKEN_TYPE, token)
            .content(super.objectMapper.writeValueAsString(request))
            .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().isOk());

        PostDetailResponse postDetailResponse = requestOnePost(1L);
        assertThat(postDetailResponse.getTitle()).isEqualTo("변경된 제목");
    }

    private void requestDeletePost(Long id, String token) throws Exception {
        super.mockMvc.perform(delete("/posts/" + id)
            .header(AUTHORIZATION, TOKEN_TYPE, token)
        )
            .andExpect(status().isNoContent());
    }
}