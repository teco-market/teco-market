package com.teco.market.oauth2.ui.web;

import static org.springframework.web.context.request.RequestAttributes.*;

import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.teco.market.domain.member.Member;
import com.teco.market.domain.member.MemberService;
import com.teco.market.exception.InvalidAuthenticationException;
import com.teco.market.exception.NotFoundMemberException;
import lombok.AllArgsConstructor;

@AllArgsConstructor
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
        Long id = (Long)webRequest.getAttribute("id", SCOPE_REQUEST);
        try {
            return memberService.findMemberById(id);
        } catch (NotFoundMemberException e) {
            throw new InvalidAuthenticationException();
        }
    }
}
