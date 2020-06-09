package com.teco.market;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.teco.market.member.Role;
import com.teco.market.oauth2.util.JwtTokenProvider;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public class TestUtil {
    public static final String AUTHORIZATION = "authorization";
    public static final String TOKEN_TYPE = "Bearer";
    public static final String SECOND_GENERATION_NAME = "utecruise";

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    protected String guestToken() {
        return jwtTokenProvider.create(1L, Role.GUEST);
    }

    protected String userToken() {
        return jwtTokenProvider.create(2L, Role.USER);
    }
}
