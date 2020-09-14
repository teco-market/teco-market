package com.teco.market.support;

import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.apache.http.HttpHeaders;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.teco.market.infra.oauth2.LoginFixture;

@WebMvcTest
public class BaseControllerTestUtil {
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp(WebApplicationContext webApplicationContext) {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
            .addFilters(new CharacterEncodingFilter("UTF-8", true))
            .alwaysDo(print())
            .build();
    }

    <T> ResultActions doPost(String path, Class<T> request) throws Exception {
        return mockMvc.perform(post(path)
            .header(LoginFixture.getUserTokenHeader())
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsBytes(request))
        )
            .andExpect(status().isCreated())
            .andExpect(header().string(HttpHeaders.LOCATION, path + "/1"));
    }

    ResultActions doGet(String path) throws Exception {
        return mockMvc.perform(get(path)
            .header(LoginFixture.getUserTokenHeader())
            .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().isOk());
    }

    <T> ResultActions doPut(String path, Class<T> request) throws Exception {
        return mockMvc.perform(put(path)
            .header(LoginFixture.getUserTokenHeader())
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsBytes(request))
        )
            .andExpect(status().isOk())
            .andExpect(header().string(HttpHeaders.LOCATION, path));
    }

    void doDelete(String path) throws Exception {
        mockMvc.perform(delete(path)
            .header(LoginFixture.getUserTokenHeader())
        )
            .andExpect(status().isNoContent());
    }
}
