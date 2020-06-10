package com.teco.market.search;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
public class StudySearch {
    private String name;
    private String total;

    public StudySearch(String name, String total) {
        this.name = name;
        this.total = total;
    }
}
