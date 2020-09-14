package com.teco.market.generation;

import java.time.LocalDateTime;

public class GenerationFixture {
    public static final String ALIAS = "우테크루즈";
    public static final LocalDateTime NOW = LocalDateTime.now();

    public static Generation createWithId(Long id) {
        return Generation.builder()
            .id(id)
            .alias(ALIAS)
            .createdAt(NOW)
            .updatedAt(NOW)
            .build();
    }

    public static GenerationCreateRequest createRequestWithId() {
        return new GenerationCreateRequest(ALIAS);
    }
}
