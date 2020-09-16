package com.teco.market.support;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teco.market.member.domain.Member;
import com.teco.market.support.annotation.Permission;

public class SupportUtil {
    public static final String TOKEN_TYPE = "Bearer ";
    public static final String MOCK_TOKEN = "MOCK_TOKEN";
    public static final String KAKAO_ID = "1123123";

    @RestController
    static class TestController {
        @Permission(target = "NONE")
        @RequestMapping("/api/none")
        public void testNoneMethod(@TestAnnotation Member member) {
        }

        @Permission(target = "GUEST")
        @RequestMapping("/api/guest")
        public void testGuestMethod(@TestAnnotation Member member) {
        }

        @Permission(target = "USER")
        @RequestMapping("/api/user")
        public void testUserMethod(@LoginMember Member member) {
        }

        @Permission(target = "ADMIN")
        @RequestMapping("/api/admin")
        public void testAdminMethod(@TestAnnotation Member member) {
        }

        public void notHandlerMethod(@TestAnnotation Member member) {
        }
    }
}
