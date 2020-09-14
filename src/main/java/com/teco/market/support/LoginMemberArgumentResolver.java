package com.teco.market.support;

import static org.springframework.web.context.request.RequestAttributes.*;

import java.util.Objects;

import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.teco.market.common.exception.notfound.NotFoundMemberException;
import com.teco.market.member.application.MemberService;
import com.teco.market.member.domain.Member;
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
    public Member resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
        NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        String attribute = (String)webRequest.getAttribute("kakaoId", SCOPE_REQUEST);

        if (Objects.isNull(attribute)) {
            throw new AssertionError("Cannot find KakaoId NativeWebRequest attribute!");
        }

        try {
            long kakaoId = Long.parseLong(attribute);
            Member byKakaoId = memberService.findByKakaoId(kakaoId);
            return byKakaoId;
        } catch (NumberFormatException e) {
            throw new NotFoundMemberException();
        }
    }
}
