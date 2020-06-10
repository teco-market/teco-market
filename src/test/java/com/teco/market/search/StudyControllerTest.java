package com.teco.market.search;

import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;

import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MvcResult;

import com.teco.market.TestUtil;

class StudyControllerTest extends TestUtil {
    @Test
    void name() throws Exception {
        MvcResult mvcResult = super.mockMvc.perform(get("/study?name=KIM&total=10"))
            .andReturn();
        System.out.println(mvcResult.getResponse().getContentAsString());
    }
}