package com.teco.market.acceptance;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.DynamicTest.*;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.type.TypeReference;
import com.teco.market.TestUtil;
import com.teco.market.comment.web.CommentResponse;
import com.teco.market.comment.web.MyCommentResponse;
import com.teco.market.comment.service.CommentUpdateRequest;
import com.teco.market.web.CustomPage;
import com.teco.market.comment.web.CommentRequest;
import com.teco.market.post.web.PostDetailResponse;

public class CommentAcceptanceTest extends TestUtil {
    private PostDetailResponse postDetailResponse;
    private String token;

    @Transactional
    @TestFactory
    Stream<DynamicTest> manageComment() {
        return Stream.of(
            dynamicTest("특정 글에, 댓글을 작성한다.", () -> {
                token = userToken();
                createComment();
            }),
            dynamicTest("작성한 댓글을 수정한다", this::updateComment),
            dynamicTest("자신이 작성한 댓글을 모두 조회한다.", () -> {
                createComment();
                createComment();
                findMyComments();
            }),
            dynamicTest("특정 댓글을 삭제한다.", this::deleteComment),
            dynamicTest("게시물을 삭제하면, 전체 댓글이 같이 삭제된다", () -> {
                deletePost();
                noComments();
            })
        );
    }

    private void updateComment() throws Exception {
        CommentUpdateRequest request = new CommentUpdateRequest("변경된 댓글");
        super.mockMvc.perform(put("/comments/1")
            .header(AUTHORIZATION, TOKEN_TYPE, token)
            .content(super.objectMapper.writeValueAsString(request))
            .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().isOk());

        checkUpdated();
    }

    private void checkUpdated() throws Exception {
        MvcResult mvcResult = super.mockMvc.perform(get("/comments/1"))
            .andExpect(status().isOk())
            .andReturn();
        CommentResponse commentResponse = super.objectMapper.readValue(mvcResult.getResponse().getContentAsString(),
            CommentResponse.class);
        assertThat(commentResponse.getContent()).isEqualTo("변경된 댓글");
    }

    private void createComment() throws Exception {
        PostDetailResponse postDetailResponse = requestPost(1L);
        CommentRequest comment = new CommentRequest("첫번째 댓글");

        super.mockMvc.perform(post("/posts/" + postDetailResponse.getId() + "/comments")
            .header(AUTHORIZATION, TOKEN_TYPE, token)
            .contentType(MediaType.APPLICATION_JSON)
            .content(super.objectMapper.writeValueAsString(comment))
        )
            .andExpect(status().isCreated());
    }

    private PostDetailResponse requestPost(Long id) throws Exception {
        MvcResult mvcResult = super.mockMvc.perform(get("/posts/" + id))
            .andExpect(status().isOk())
            .andReturn();
        PostDetailResponse postDetailResponse = super.objectMapper.readValue(
            mvcResult.getResponse().getContentAsString(), PostDetailResponse.class);
        assertThat(postDetailResponse).isNotNull();
        assertThat(postDetailResponse.getId()).isEqualTo(1);
        this.postDetailResponse = postDetailResponse;
        return postDetailResponse;
    }

    private void findMyComments() throws Exception {
        Page<MyCommentResponse> result = getMyComments();

        assertThat(result.getTotalElements()).isEqualTo(3);
    }

    private Page<MyCommentResponse> getMyComments() throws Exception {
        MvcResult mvcResult = super.mockMvc.perform(get("/me/comments")
            .header(AUTHORIZATION, TOKEN_TYPE, token)
        )
            .andExpect(status().isOk())
            .andReturn();
        return super.objectMapper.readValue(
            mvcResult.getResponse().getContentAsString(),
            new TypeReference<CustomPage<MyCommentResponse>>() {});
    }

    private void deleteComment() throws Exception {
        super.mockMvc.perform(delete("/comments/1")
            .header(AUTHORIZATION, TOKEN_TYPE, token)
        )
            .andExpect(status().isNoContent());

        super.mockMvc.perform(get("/comments/1")
            .header(AUTHORIZATION, TOKEN_TYPE, token)
        )
            .andExpect(status().isBadRequest());
    }

    private void deletePost() throws Exception {
        super.mockMvc.perform(delete("/posts/1")
            .header(AUTHORIZATION, TOKEN_TYPE, token)
        )
            .andExpect(status().isNoContent());
    }

    private void noComments() throws Exception {
        Page<MyCommentResponse> myComments = getMyComments();
        assertThat(myComments.getTotalElements()).isEqualTo(0);
    }
}
