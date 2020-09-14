package com.teco.market.support;

import static org.assertj.core.api.Assertions.*;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.apache.http.HttpHeaders;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.teco.market.infra.oauth2.LoginFixture;
import com.teco.market.member.web.MemberResponse;

public class BaseControllerTestUtil {
    protected MockMvc mockMvc;

    protected ObjectMapper objectMapper;

    @BeforeEach
    void setUp(WebApplicationContext webApplicationContext) {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
            .addFilters(new CharacterEncodingFilter("UTF-8", true))
            .alwaysDo(print())
            .build();
        objectMapper = new ObjectMapper();
    }

    protected <T> ResultActions doPost(String path, T request) throws Exception {
        return mockMvc.perform(post(path)
            .header(HttpHeaders.AUTHORIZATION, LoginFixture.getUserTokenHeader())
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsBytes(request))
        )
            .andExpect(status().isCreated())
            .andExpect(header().string(HttpHeaders.LOCATION, path + "/1"));
    }

    protected <T> ResultActions doGet(String path, T expected) throws Exception {
        ResultActions result = mockMvc.perform(get(path)
            .header(HttpHeaders.AUTHORIZATION, LoginFixture.getUserTokenHeader())
            .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().isOk());

        String responseBody = result.andReturn()
            .getResponse()
            .getContentAsString();

        assertThat(objectMapper.readValue(responseBody, MemberResponse.class))
            .isEqualToComparingFieldByField(expected);

        return result;
    }

    protected <T> ResultActions doPut(String path, T request) throws Exception {
        return mockMvc.perform(put(path)
            .header(HttpHeaders.AUTHORIZATION, LoginFixture.getUserTokenHeader())
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsBytes(request))
        )
            .andExpect(status().isNoContent());
    }

    protected <T> ResultActions doPatch(String path, T request) throws Exception {
        return mockMvc.perform(patch(path)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsBytes(request))
        )
            .andExpect(status().isNoContent());
    }

    protected void doDelete(String path) throws Exception {
        mockMvc.perform(delete(path)
            .header(HttpHeaders.AUTHORIZATION, LoginFixture.getUserTokenHeader())
        )
            .andExpect(status().isNoContent());
    }
}
