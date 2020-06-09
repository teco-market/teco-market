package com.teco.market.config;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.teco.market.oauth2.web.AllowRoleArgumentResolver;
import com.teco.market.oauth2.web.LoginMemberArgumentResolver;
import com.teco.market.oauth2.web.interceptor.BearerAuthInterceptor;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    private final BearerAuthInterceptor bearerAuthInterceptor;
    private final LoginMemberArgumentResolver loginMemberArgumentResolver;
    private final AllowRoleArgumentResolver allowRoleArgumentResolver;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(bearerAuthInterceptor);
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(loginMemberArgumentResolver);
        resolvers.add(allowRoleArgumentResolver);
    }
}
