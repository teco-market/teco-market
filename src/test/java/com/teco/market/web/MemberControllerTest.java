package com.teco.market.web;

import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.teco.market.TestUtil;
import com.teco.market.member.MemberService;
import com.teco.market.member.web.MemberRequiredUpdateRequest;

class MemberControllerTest extends TestUtil {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    MemberService memberService;

    @Test
    void updatedMember() throws Exception {
        MemberRequiredUpdateRequest request = new MemberRequiredUpdateRequest("KILE", 1L);
        this.mockMvc.perform(put("/me")
            .header("Authorization", "Bearer", guestToken())
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(request))
        )
            .andExpect(status().isOk());

        verify(memberService).updateRequiredInfo(any(),any());
    }
}