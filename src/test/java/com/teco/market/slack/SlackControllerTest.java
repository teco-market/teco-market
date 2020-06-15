package com.teco.market.slack;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import com.teco.market.TestUtil;
import com.teco.market.slack.notify.SlackMessageAttachment;
import com.teco.market.slack.notify.SlackMessageAttachment.SlackMessageAttachmentBuilder;

class SlackControllerTest extends TestUtil {
    @Test
    void notifyAlarm() throws Exception {
        SlackMessageAttachmentBuilder message = SlackMessageAttachment.builder()
            .color("RED")
            .pretext("디디 븅슨")
            .title("우리팀!")
            .titleLink("https://www.google.com")
            .text("잘해보자!!");
        super.mockMvc.perform(post("/notify")
            .contentType(MediaType.APPLICATION_JSON)
            .content(super.objectMapper.writeValueAsString(message))
        )
            .andExpect(status().isOk())
            .andDo(print());
    }
}