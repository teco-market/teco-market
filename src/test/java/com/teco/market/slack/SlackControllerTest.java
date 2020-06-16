package com.teco.market.slack;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import com.teco.market.TestUtil;
import com.teco.market.slack.notify.SlackMessageRequest;

class SlackControllerTest extends TestUtil {
    @Test
    void notifyAlarm() throws Exception {
        String token = kyleToken();
        SlackMessageRequest request = new SlackMessageRequest(4L, 3L);

        super.mockMvc.perform(post("/notify")
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(super.objectMapper.writeValueAsString(request))
            .header(AUTHORIZATION, TOKEN_TYPE, token)
        )
            .andExpect(status().isOk())
            .andDo(print());
    }
}