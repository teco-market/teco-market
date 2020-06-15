package com.teco.market.acceptance;


import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;

import com.teco.market.TestUtil;

public class SearchAcceptanceTest extends TestUtil {
    @Test
    void selectByRecent() throws Exception {
        super.mockMvc.perform(get("/search")
            .param("byPriceAsc", "true")
        )
            .andExpect(status().isOk())
            .andDo(print());
    }

}
