package com.teco.market.support;

import com.teco.market.member.Member;

public class SupportUtil {
    public static final String TOKEN_TYPE = "Bearer ";
    public static final String MOCK_TOKEN = "MOCK_TOKEN";
    public static final String KAKAO_ID = "MOCK_KAKAO_ID";


    static class TestController {
        public void testMethod(@LoginMember Member member) { }

        public void notHandlerMethod(){ }

        public void excludePattern(){ }
    }
}
