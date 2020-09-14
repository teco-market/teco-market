package com.teco.market.infra.oauth2;

import static com.teco.market.infra.oauth2.LoginFixture.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import com.teco.market.infra.oauth2.service.LoginService;
import com.teco.market.infra.oauth2.web.LoginController;
import com.teco.market.support.BearerInterceptor;
import com.teco.market.support.LoginMemberArgumentResolver;

@WebMvcTest(controllers = LoginController.class)
class LoginControllerTest {
    @MockBean
    private BearerInterceptor interceptor;

    @MockBean
    private LoginService loginService;

    @MockBean
    private LoginMemberArgumentResolver argumentResolver;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup(WebApplicationContext webApplicationContext, RestDocumentationContextProvider restDocumentation) {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
            .apply(documentationConfiguration(restDocumentation))
            .addFilters(new CharacterEncodingFilter("UTF-8", true))
            .alwaysDo(print())
            .build();
    }

    @DisplayName("토큰 생성 후 토큰이 담긴 화면으로 Redirection 한다.")
    @Test
    void redirectTokenPageTest() throws Exception {
        given(interceptor.preHandle(any(HttpServletRequest.class), any(HttpServletResponse.class), any()))
            .willReturn(true);
        given(loginService.createJwtTokenUrl(CODE_VALUE)).willReturn(URL + "?access_token=" + ACCESS_TOKEN);

        mockMvc.perform(get("/api/login/token")
            .param("code", CODE_VALUE)
        )
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl(URL + "?access_token=" + ACCESS_TOKEN))
            .andExpect(header().string("location", URL + "?access_token=" + ACCESS_TOKEN));
    }

    @DisplayName("로그인 완료 후 성공 시 토큰을 반환한다.")
    @Test
    public void loginSuccessTest() throws Exception {
        given(interceptor.preHandle(any(HttpServletRequest.class), any(HttpServletResponse.class), any()))
            .willReturn(true);

        mockMvc.perform(get("/api/login/check")
            .param(ACCESS_TOKEN, USER_TOKEN)
            .param(SUCCESS, LOGIN_SUCCESS)
        )
            .andExpect(status().isOk())
            .andExpect(jsonPath("accessToken").value(USER_TOKEN));
    }

    @DisplayName("로그인 실패 시 Unauthorize 상태를 반환한다.")
    @Test
    public void loginFailTest() throws Exception {
        given(interceptor.preHandle(any(), any(), any())).willReturn(true);

        mockMvc.perform(get("/api/login/check")
            .param(SUCCESS, LOGIN_FAIL)
        )
            .andExpect(status().isUnauthorized());
    }
}