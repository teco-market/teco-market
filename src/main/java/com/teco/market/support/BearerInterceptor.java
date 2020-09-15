package com.teco.market.support;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import com.teco.market.common.exception.AuthorizationException;
import com.teco.market.common.exception.notfound.NotFoundMemberException;
import com.teco.market.infra.oauth2.JwtTokenProvider;
import com.teco.market.member.application.MemberService;
import com.teco.market.member.domain.Member;
import com.teco.market.member.domain.Role;
import com.teco.market.support.annotation.AnnotationUtils;
import com.teco.market.support.annotation.Permission;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class BearerInterceptor implements HandlerInterceptor {
    private final AuthenticationExtractor extractor;
    private final JwtTokenProvider provider;
    private final MemberService memberService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        Role role = getProperRole((HandlerMethod)handler);
        if (role.isNone()) {
            return true;
        }
        String bearer = extractor.extract(request);
        String kakaoId = provider.getSubject(bearer);
        Member findMember = findMember(kakaoId);
        request.setAttribute("member", findMember);
        if (role.isGuest() || findMember.hasPermission(role)) {
            return true;
        }
        throw new AuthorizationException();
    }

    private Member findMember(String kakaoId) {
        try {
            return memberService.findByKakaoId(Long.parseLong(kakaoId));
        } catch (NumberFormatException e) {
            throw new NotFoundMemberException();
        }
    }

    private Role getProperRole(HandlerMethod handler) {
        Permission clazzAnnotation = handler.getBean().getClass().getAnnotation(Permission.class);
        Permission methodAnnotation = handler.getMethod().getAnnotation(Permission.class);
        Permission priority = AnnotationUtils.getPriority(clazzAnnotation, methodAnnotation);
        return Role.valueOf(priority.target());
    }
}
