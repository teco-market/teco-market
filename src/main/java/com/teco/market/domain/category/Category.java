package com.teco.market.domain.category;

import javax.persistence.Entity;

import com.teco.market.domain.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Category extends BaseEntity {

    private String name;

    public Category(String name) {
        this.name = name;
    }
}
