package com.teco.market.support;

import static com.teco.market.support.SupportUtil.*;
import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import org.apache.http.HttpHeaders;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.mock.web.MockHttpServletRequest;

class AuthenticationExtractorTest {
    private AuthenticationExtractor extractor;
    private MockHttpServletRequest request;

    @BeforeEach
    void setUp() {
        extractor = new AuthenticationExtractor();
        request = new MockHttpServletRequest();
    }

    @DisplayName("요청의 헤더에서 토큰을 추출한다.")
    @Test
    void extract() {
        request.addHeader(HttpHeaders.AUTHORIZATION, TOKEN_TYPE + MOCK_TOKEN);

        assertThat(extractor.extract(request)).isEqualTo(MOCK_TOKEN);
    }

    @DisplayName("올바르지 않은 토큰인 경우 예외를 반환한다.")
    @ParameterizedTest
    @ValueSource(strings = {"", "Barer abc", "Digest ABC"})
    void invalidTokenExtractTest(String expectedToken) {
        request.addHeader("Authorization", expectedToken);
        assertThatThrownBy(() -> extractor.extract(request))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("유효하지 않은 토큰입니다.");
    }
}