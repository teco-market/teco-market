package com.teco.market.generation;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class GenerationCreateRequest {
    private String alias;

    public GenerationCreateRequest(String alias) {
        this.alias = alias;
    }

    public Generation toGeneration() {
        return new Generation(this.alias);
    }
}
