package com.teco.market.oauth2.ui.web;

import static org.springframework.web.context.request.RequestAttributes.*;

import java.util.Objects;

import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.teco.market.domain.member.Role;
import com.teco.market.exception.invalid.InvalidAuthenticationException;

@Component
public class AllowRoleArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasMethodAnnotation(AllowRole.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
        NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        Role role = Role.valueOf((String)webRequest.getAttribute("role", SCOPE_REQUEST));
        Role[] allowedRoles = Objects.requireNonNull(parameter.getMethodAnnotation(AllowRole.class)).roles();
        for (Role allowedRole : allowedRoles) {
            if (role == allowedRole) {
                return null;
            }
        }
        throw new InvalidAuthenticationException();
    }
}