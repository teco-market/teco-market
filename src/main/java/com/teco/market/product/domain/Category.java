package com.teco.market.product.domain;

import java.util.Arrays;

import com.teco.market.product.exception.NotFoundCategoryException;

public enum Category {
    HI;

    public static Category of(String category) {
        return Arrays.stream(values())
            .filter(value -> value.name().equalsIgnoreCase(category))
            .findAny()
            .orElseThrow(() -> new NotFoundCategoryException("asdsad"));
    }
}
