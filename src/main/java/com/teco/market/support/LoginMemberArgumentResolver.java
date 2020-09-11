package com.teco.market.support.annotation;

import static org.springframework.web.context.request.RequestAttributes.*;

import java.util.Objects;

import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.teco.market.member.MemberService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class LoginMemberArgumentResolver implements HandlerMethodArgumentResolver {
    private final MemberService memberService;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(LoginMember.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
        NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        final String attribute = (String)webRequest.getAttribute("kakaoId", SCOPE_REQUEST);

        if (Objects.isNull(attribute)) {
            throw new AssertionError("Cannot find loginMemberKakaoId NativeWebRequest attribute!");
        }

        try {
            final long kakaoId = Long.parseLong(attribute);
            return memberService.findByKakaoId(kakaoId);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Cannot find Member");
        }
    }
}
