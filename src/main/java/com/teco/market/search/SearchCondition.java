package com.teco.market.search;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchCondition {
    private Long categoryId;
    private Boolean byLike;
    private Boolean byRecent;
    private Boolean byPriceDesc;
    private Boolean byPriceAsc;
    private String keyword;
    private String author;

    public SearchCondition() {
    }
}
