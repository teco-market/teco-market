package com.teco.market.infra.oauth2;

import reactor.core.publisher.Mono;

public interface LoginAPIService<T, U> {
    String createTokenUrl(final JwtTokenResponse token);

    Mono<T> fetchOAuthToken(final String code);

    Mono<U> fetchUserInfo(final T t);
}
